const loginBox = $('.login-box');
const registerBox = $('.register-box');
const forgotPasswordBox = $('.forgot-password-box');

function openLoginForm() {
    showLoginForm();
    setTimeout(function(){
        $('#modal-account').modal('show');
    }, 230);
}

function openRegisterForm() {
    loginBox.hide();
    forgotPasswordBox.hide();
    registerBox.fadeIn('fast');
    setTimeout(function(){
        $('#modal-account').modal('show');
    }, 230);
}

function openForgotPasswordForm() {
    loginBox.hide();
    registerBox.hide();
    forgotPasswordBox.fadeIn('fast');
    setTimeout(function(){
        $('#modal-account').modal('show');
    }, 230);
}

function showRegisterForm() {
    loginBox.fadeOut('fast', function () {
        registerBox.fadeIn('fast');
    });
}

function showLoginForm() {
    if (forgotPasswordBox.is(':visible')) {
        forgotPasswordBox.fadeOut('fast', function () {
            loginBox.fadeIn('fast');
        });
    } else if (registerBox.is(':visible'))  {
        registerBox.fadeOut('fast', function () {
            loginBox.fadeIn('fast');
            cleanRegisterForm();
        });
    } else {
        registerBox.hide();
        forgotPasswordBox.hide();
        loginBox.fadeIn('fast');
    }
}

function showForgotPasswordForm() {
    loginBox.fadeOut('fast', function () {
        forgotPasswordBox.fadeIn('fast');
    });
}

function submitRegisterForm() {
    let form = document.getElementById('register-form');

    if (form.checkValidity()) {
        form.submit();
    } else {
        validateForm();
    }
}

function cleanRegisterForm() {
    $('.popover').popover('hide');
    $('#register-form').trigger('reset');
    $('.is-valid').removeClass('is-valid');
    $('.is-invalid').removeClass('is-invalid');
}