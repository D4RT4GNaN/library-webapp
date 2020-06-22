/* Validation Messages */
var $noEmptyField="";
var $mustBeAnAddress="";
var $emailRequired="";
var $noMatchConfirmation="";
var $validationLength="";
var $lowerCase="";
var $upperCase="";
var $numbers="";
var $blankSpace="";

/* Email Validation - RFC822 */
emailRegex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;

/**
 * Check if the password matches the restrictions imposed.
 * */
function validatePassword(id) {
    var input = $('#' + id);
    var inputValue = input.val();

    var digitsString = inputValue.replace(/[^0-9]/g,"");
    var lowerCaseNumber = inputValue.replace(/[^a-z]/g,"").length;
    var upperCaseNumber = inputValue.replace(/[^A-Z]/g,"").length;

    var hasError = false;
    var message="";

    if (!inputValue.match(/^.{8,30}$/g)) {
        hasError = true;
        message = addMessage(message,$validationLength);
    } if (lowerCaseNumber < 3) {
        hasError = true;
        message = addMessage(message, $lowerCase);
    } if (upperCaseNumber < 1) {
        hasError = true;
        message = addMessage(message,$upperCase);
    } if (!digitsString.match(/^.{1,5}$/g)) {
        hasError = true;
        message = addMessage(message, $numbers);
    } if (inputValue.match(/\s/)) {
        hasError = true;
        message = addMessage(message, $blankSpace);
    }

    if (!hasError) {
        validInput(input);
    } else {
        invalidInput(input, message);
    }
}



/**
 * Checks whether the confirmation matches the password.
 * */
function validateConfirmPassword(passwordId, confirmId) {
    var confirmInput = $('#' + confirmId);
    var confirmInputValue = confirmInput.val();

    var passwordInputValue = $('#' + passwordId).val();

    if (confirmInputValue === "" || !(passwordInputValue === confirmInputValue)) {
        invalidInput(confirmInput,$noMatchConfirmation);
    } else {
        validInput(confirmInput);
    }
}



/**
 * Checks if the entered email is an email according to the RFC822 standard.
 * */
function validateEmail() {
    var emailInput = $('#register-email');
    var emailInputValue = emailInput.val();

    if (emailInputValue.match(emailRegex)) {
        validInput(emailInput)
    } else {
        invalidInput(emailInput, $emailRequired)
    }
}



/**
 * Checks if the address has a number, a label, a zip code and a city using a regex.
 * */
function validateAddress() {
    var addressInput = $('#register-address');
    var addressInputValue = addressInput.val();
    var addressRegex = /^\d+\s([A-Z]?[a-z]+\s){2}\d{5}\s[A-Z]?[a-z]+$/;

    if (addressInputValue.match(addressRegex)) {
        validInput(addressInput);
    } else {
        invalidInput(addressInput, $mustBeAnAddress)
    }
}



/**
 * Checks if the field to be validated is not empty.
 * */
function validateBasicData(id) {
    var input = $('#' + id);
    var inputValue = input.val();

    if (inputValue === "") {
        invalidInput(input, $noEmptyField)
    } else {
        validInput(input);
    }
}



/**
 * Validates the registration form.
 * */
function validateForm() {
    validateEmail();
    validatePassword("register-password");
    validateConfirmPassword("register-password", "register-confirm-password");
    validateBasicData("register-firstname");
    validateBasicData("register-lastname");
    validateAddress();
}



/**
 * Turn the field green and hide the popover.
 * */
function validInput(input) {
    input.removeClass("is-invalid").addClass("is-valid");
    input.popover('hide');
}



/**
 * Turns the field red and displays the popover containing the errors.
 * */
function invalidInput(input, message) {
    input.removeClass("is-valid").addClass("is-invalid");
    input.popover({content: message, html: true});
    input.popover('show');
}



/**
 * Create the message with the errors that will be displayed in the popover.
 * */
function addMessage(previousMessage, addedMessage) {
    if (previousMessage !== "") {
        previousMessage += "<br/>";
    }
    previousMessage += addedMessage;
    return previousMessage;
}



/**
 * Fetches messages in the right language from .properties files
 * */
function internationalization(
    noEmptyField, mustBeAnAddress, emailRequired, noMatchConfirmation,
    validationLength, lowerCase, upperCase, numbers, blankSpace) {
    $noEmptyField = noEmptyField;
    $mustBeAnAddress = mustBeAnAddress;
    $emailRequired = emailRequired;
    $noMatchConfirmation = noMatchConfirmation;
    $validationLength = validationLength;
    $lowerCase = lowerCase;
    $upperCase = upperCase;
    $numbers = numbers;
    $blankSpace = blankSpace;
}