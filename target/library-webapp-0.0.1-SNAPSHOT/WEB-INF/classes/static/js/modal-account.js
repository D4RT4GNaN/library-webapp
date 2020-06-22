/* Modals */
loginBox = $('.login-box');
registerBox = $('.register-box');
forgotPasswordBox = $('.forgot-password-box');
resendEmailBox = $('.resend-email-box');



/**
 * Opens the modal corresponding to the URL error.
 * */
url = window.location.href;
if(url.indexOf('loginError=true') !== -1) {
    openLoginForm();
} else if (url.indexOf('registerError=true') !== -1) {
    openRegisterForm();
} else if (url.indexOf('forgotPasswordError=true') !== -1) {
    openForgotPasswordForm();
} else if(url.indexOf('emailSendingError=true') !== -1) {
    openResendEmailForm();
} else if (url.indexOf('confirmEmailSuccess=true') !== -1) {
    alert('email confirmed !');
}



/**
 * Opens the login modal.
 * */
function openLoginForm() {
    showLoginForm();
    setTimeout(function(){
        $('#modal-account').modal('show');
    }, 230);
}



/**
 * Opens the register modal.
 * */
function openRegisterForm() {
    loginBox.hide();
    forgotPasswordBox.hide();
    resendEmailBox.hide();
    registerBox.fadeIn('fast');
    setTimeout(function(){
        $('#modal-account').modal('show');
    }, 230);
}



/**
 * Opens the forgot password modal.
 * */
function openForgotPasswordForm() {
    loginBox.hide();
    registerBox.hide();
    resendEmailBox.hide();
    forgotPasswordBox.fadeIn('fast');
    setTimeout(function(){
        $('#modal-account').modal('show');
    }, 230);
}



/**
 * Opens the resend email modal.
 * */
function openResendEmailForm() {
    loginBox.hide();
    registerBox.hide();
    forgotPasswordBox.hide();
    resendEmailBox.fadeIn('fast');
    setTimeout(function(){
        $('#modal-account').modal('show');
    }, 230);
}



/**
 * Transition from the login modal to the registration modal.
 * */
function showRegisterForm() {
    loginBox.fadeOut('fast', function () {
        registerBox.fadeIn('fast');
    });
}



/**
 * Transition from one modal to the login modal.
 * */
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
    } else if (resendEmailBox.is(':visible')) {
        resendEmailBox.fadeOut('fast', function () {
           loginBox.fadeIn('fast');
        });
    } else {
        registerBox.hide();
        forgotPasswordBox.hide();
        resendEmailBox.hide();
        loginBox.fadeIn('fast');
    }
}



/**
 * Transition from the login modal to the forgot password modal.
 * */
function showForgotPasswordForm() {
    loginBox.fadeOut('fast', function () {
        forgotPasswordBox.fadeIn('fast');
    });
}



/**
 * Transition from the registration modal to the email forwarding modal.
 * */
function showResendEmailForm() {
    registerBox.fadeOut('fast', function () {
        resendEmailBox.fadeIn('fast');
    });
}



/**
 * Submission of the registration form through its validation.
 * */
function submitRegisterForm() {
    var form = document.getElementById('register-form');

    if (form.checkValidity()) {
        registerBox.fadeOut('fast');
        $('.loaded').removeClass("loaded");
        form.submit();
    } else {
        validateForm();
    }
}



/**
 * Reset the register modal
 * */
function cleanRegisterForm() {
    $('.popover').popover('hide');
    $('#register-form').trigger('reset');
    $('.is-valid').removeClass('is-valid');
    $('.is-invalid').removeClass('is-invalid');
}