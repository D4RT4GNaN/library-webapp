/** VALIDATION **/
const emailRegex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;

function validatePassword(id) {
    let input = $('#' + id);
    let inputValue = input.val();

    let digitsString = inputValue.replace(/[^0-9]/g,"");
    let lowerCaseNumber = inputValue.replace(/[^a-z]/g,"").length;
    let upperCaseNumber = inputValue.replace(/[^A-Z]/g,"").length;

    let hasError = false;
    let message="";

    if (!inputValue.match(/^.{8,30}$/g)) {
        hasError = true;
        message = addMessage(message,"trop long ou trop court");
    } if (lowerCaseNumber < 3) {
        hasError = true;
        message = addMessage(message, "minimum 3 lettres minuscule");
    } if (upperCaseNumber < 1) {
        hasError = true;
        message = addMessage(message,"minimum 1 lettre majuscule");
    } if (!digitsString.match(/^.{1,5}$/g)) {
        hasError = true;
        message = addMessage(message, "pas de chiffre ou trop de chiffres");
    } if (inputValue.match(/\s/)) {
        hasError = true;
        message = addMessage(message, "les espaces sont interdits");
    }

    if (!hasError) {
        validInput(input);
    } else {
        invalidInput(input, message);
    }
}

function validateConfirmPassword(passwordId, confirmId) {
    let confirmInput = $('#' + confirmId);
    let confirmInputValue = confirmInput.val();

    let passwordInputValue = $('#' + passwordId).val();

    if (confirmInputValue === "" || !(passwordInputValue === confirmInputValue)) {
        invalidInput(confirmInput,"le mot de passe et sa confirmation ne correspondent pas");
    } else {
        validInput(confirmInput);
    }
}

function validateEmail() {
    let emailInput = $('#register-email');
    let emailInputValue = emailInput.val();

    if (emailInputValue.match(emailRegex)) {
        validInput(emailInput)
    } else {
        invalidInput(emailInput, "un email est demandé")
    }
}

function validateAddress() {
    let addressInput = $('#register-address');
    let addressInputValue = addressInput.val();
    let addressRegex = /^\d+\s([A-Z]?[a-z]+\s){2}\d{5}\s[A-Z]?[a-z]+$/;

    if (addressInputValue.match(addressRegex)) {
        validInput(addressInput);
    } else {
        invalidInput(addressInput, "Une adresse est demandée !")
    }
}

function validateBasicData(id) {
    let input = $('#' + id);
    let inputValue = input.val();

    if (inputValue === "") {
        invalidInput(input, "Le champ ne doit pas être vide !")
    } else {
        validInput(input);
    }
}

function validateForm() {
    validateEmail();
    validatePassword("register-password");
    validateConfirmPassword("register-password", "register-confirm-password");
    validateBasicData("register-firstname");
    validateBasicData("register-lastname");
    validateAddress();
}

function validInput(input) {
    input.removeClass("is-invalid").addClass("is-valid");
    input.popover('hide');
}

function invalidInput(input, message) {
    input.removeClass("is-valid").addClass("is-invalid");
    input.popover({content: message, html: true});
    input.popover('show');
    console.log(message);
}

function addMessage(previousMessage, addedMessage) {
    if (previousMessage !== "") {
        previousMessage += "<br/>";
    }
    previousMessage += addedMessage;
    return previousMessage;
}

/** END VALIDATION **/