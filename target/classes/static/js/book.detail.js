/**
 * Ajax method to send the comment to the server and reload the comment list to display it.
 * */
function sendComment(reference) {
    var url = "/book-detail/" + reference + "/comment";
    var $form = $('#addCommentForm form');

    $form.on('submit', function(e) {
        e.preventDefault();
        $.ajax({
            url : url,
            type : 'post',
            data : $form.serialize(),
            success : function(response) {
                if ($(response).find('.has-error').length) {
                    $form.replaceWith(response);
                }
                else{
                    $('.commentContainer').replaceWith(response);
                    commentDataTable();
                }
            }
        });
    });
}



/**
 * Displays the form to add a comment
 * */
function showCommentForm() {
    $('#addCommentForm').show();
}

/**
 *
 * */
function commentDataTable() {
    $('#commentsTable').DataTable({
        "searching": false,
        "lengthChange": false,
        "pageLength": 5,
        "pagingType": "full",
        "language": {
            "paginate": {
                "first": "<< First",
                "previous": "< Previous",
                "next": "Next >",
                "last": "Last >>"
            }
        },
        "info": false,
        "ordering": false
    });
}