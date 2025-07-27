
const loginForm = document.getElementById("login-form");
const loginMessage = document.getElementById("message");

loginForm.addEventListener("submit", async function (e) {
  e.preventDefault();

  const loginData = {
    emailOrPhone: document.getElementById("emailOrPhone").value,
    password: document.getElementById("password").value,
  };

  try {
    const response = await fetch("http://localhost:8091/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(loginData),
    });

    if (response.ok) {
      localStorage.setItem("userEmail", userData.email);
      loginMessage.textContent = "Login successful! Redirecting...";
      loginMessage.style.color = "green";
      setTimeout(() => window.location.href = "dashboard.html", 1500);
    } else {
      const errorData = await response.json();
      loginMessage.textContent = errorData.message || "Login failed!";
      loginMessage.style.color = "red";
    }
  } catch (err) {
    loginMessage.textContent = "Something went wrong!";
    loginMessage.style.color = "red";
    console.error(err);
  }
});
