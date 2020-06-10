/**
 * Switch between edit form and undo form of the button
 * */
function toggleEditButton() {
    var editButton = document.querySelector("#edit-button");
    if (editButton.classList.contains("fa-edit")) {
        editButton.classList.remove("fa-edit");
        editButton.classList.add("fa-times");
    } else {
        editButton.classList.remove("fa-times");
        editButton.classList.add("fa-edit");
    }
}



/**
 * Toggles the readonly attribute of the inputs and displays the submit button in edit mode
 * */
function toggleInput() {
    $('.personal-data-input').each(function(){
        var input = $(this);
        input.is("[readonly]") ? input.removeAttr("readonly") : input.attr("readonly", "readonly");
    });

    var submitButton = $('#personal-data-submit');
    submitButton.toggle('style');
    $('#personal-data-form')[0].reset();
}



/**
 * Toggle button and inputs
 * */
function editOrCancel() {
    toggleEditButton();
    toggleInput();
}