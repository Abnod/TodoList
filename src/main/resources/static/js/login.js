var password = document.getElementById("password-reg");
var confirm_password = document.getElementById("password-confirm-reg");
var registerForm = document.getElementById("register-wrap");
var loginForm = document.getElementById("login-wrap");
var regData = document.getElementById("register-form");
var regError = document.getElementById("registerError");


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


regData.addEventListener("submit", function (event) {
    event.preventDefault();

    var object = {};
    var formData = new FormData(regData);
    formData.forEach(function (value, key) {
        object[key] = value;
    });
    var json = JSON.stringify(object);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "register");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(json);

    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) return;
        if (xhr.status !== 200) {
            // обработать ошибку
            regError.innerHTML = xhr.statusText;
        } else {
            try {
                json = JSON.parse(xhr.responseText);
            } catch (e) {
                alert("Incorrect answer: " + e.message);
            }
            if (json.success === true) {
                window.location = "/"
            } else {
                regError.innerHTML = json.message;
            }
        }
    };
});
