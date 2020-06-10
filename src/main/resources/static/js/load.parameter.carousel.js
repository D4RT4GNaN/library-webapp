/**
 * Loads a carousel containing the different elements of the parameter page.
 * */
$(document).ready(function () {
    $('.my-flipster').flipster({
        style: 'flat',
        buttons: true,
        spacing: 2,
        itemContainer: '#itemContainer',
        itemSelector: '.itemSelector',
        loop: true
    }).ready(function () {
        setTimeout(function () {
            $('body').addClass('loaded');
        }, 1000);
    });
});