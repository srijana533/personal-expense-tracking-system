// Updated dashboard.js

const addExpenseForm = document.querySelector("form");

if (addExpenseForm) {
  addExpenseForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const formData = new FormData(addExpenseForm);
    const expenseData = Object.fromEntries(formData.entries());
expenseData.amount = parseFloat(expenseData.amount);
expenseData.email = localStorage.getItem("email");  // ✅ Add this


    try {
      const email = localStorage.getItem("email");
     const response = await fetch("http://localhost:8091/api/expenses?email=${email}= uio", {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
    "X-User-Email": localStorage.getItem("userEmail") // ✅ added
  },
  body: JSON.stringify(expenseData),
      });
      if (response.ok) {
        alert("Expense added successfully!");
        window.location.reload();
      } else {
        alert("Failed to add expense");
      }
    } catch (error) {
      alert("Error occurred while adding expense");
      console.error(error);
    }
  });
}

window.onload = loadSummariesAndCharts;

async function loadSummariesAndCharts() {
  try {
    const [weekly, monthly, yearly] = await Promise.all([
      fetch("http://localhost:8091/api/expenses/weekly-summary").then((r) => r.json()),
      fetch("http://localhost:8091/api/expenses/monthly-summary").then((r) => r.json()),
      fetch("http://localhost:8091/api/expenses/yearly-summary").then((r) => r.json()),
    ]);

    showTotal("weekly-total", weekly);
    showTotal("monthly-total", monthly);
    showTotal("yearly-total", yearly);

    renderPieChart("weeklyChart", weekly, "Weekly Expenses");
    renderPieChart("monthlyChart", monthly, "Monthly Expenses");
    renderPieChart("yearlyChart", yearly, "Yearly Expenses");
  } catch (err) {
    console.error("Failed to load chart data:", err);
  }
}
function showTotal(id, data) {
  const total = Object.values(data).reduce((a, b) => a + b, 0);
  const box = document.getElementById(id);
  if (box) box.textContent = `Total: ₹${total.toFixed(2)}`;
}
function renderPieChart(canvasId, data, title) {
  const ctx = document.getElementById(canvasId).getContext("2d");
  const labels = Object.keys(data);
  const values = Object.values(data);
  new Chart(ctx, {
    type: "pie",
    data: {
      labels,
      datasets: [
        {
          label: title,
          data: values,
          backgroundColor: [
            "#0d6efd",
            "#6f42c1",
            "#fd7e14",
            "#198754",
            "#dc3545",
            "#ffc107",
          ],
        },
      ],
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: "bottom",
        },
        title: {
          display: true,
          text: title,
        },
      },
    },
  });
}