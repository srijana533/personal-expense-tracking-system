const signupForm = document.getElementById("signup-form");
const signupMessage = document.getElementById("message");

signupForm.addEventListener("submit", async function (e) {
  e.preventDefault();

 const userData = {
  username: document.getElementById("name").value,
  email: document.getElementById("email").value,
  mobileNumber: document.getElementById("phone").value,
  password: document.getElementById("password").value,
  job: document.getElementById("job").value,
  monthlyIncome: parseFloat(document.getElementById("monthlyIncome").value),
  budgetLimit: parseFloat(document.getElementById("budgetLimit").value),
};


  try {
    const response = await fetch("http://localhost:8091/api/auth/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(userData),
    });

    if (response.ok) {
      localStorage.setItem("userEmail", userData.email);
      signupMessage.textContent = "Signup successful! Redirecting...";
      signupMessage.style.color = "green";
      setTimeout(() => window.location.href = "dashboard.html", 1500);
    } else {
      const contentType = response.headers.get("content-type");
      if (contentType && contentType.includes("application/json")) {
        const errorData = await response.json();
        signupMessage.textContent = errorData.message || "Signup failed!";
      } else {
        const errorText = await response.text();
        signupMessage.textContent = errorText || "Signup failed!";
      }
      signupMessage.style.color = "red";
    }
  } catch (err) {
    signupMessage.textContent = "Something went wrong!";
    signupMessage.style.color = "red";
    console.error(err);
  }
});
