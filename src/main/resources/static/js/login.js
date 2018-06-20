var password = document.getElementById("password-reg");
var confirm_password = document.getElementById("password-confirm-reg");
var registerForm = document.getElementById("register-wrap");
var loginForm = document.getElementById("login-wrap");


var validate = function validatePassword() {
    if (password.value !== confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }
};

document.getElementById("sign-up-button").onclick = function () {
    registerForm.classList.add("active-dx");
    loginForm.classList.add("inactive-sx");
    loginForm.classList.remove("active-sx");
    registerForm.classList.remove("inactive-dx");
};

document.getElementById("back-to-login-button").onclick = function () {
    loginForm.classList.add("active-sx");
    registerForm.classList.add("inactive-dx");
    registerForm.classList.remove("active-dx");
    loginForm.classList.remove("inactive-sx");
};