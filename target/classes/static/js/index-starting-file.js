/**
 * Function launched when the site loads.
 * Animation -> Loading the carousel -> Second animation.
 * */
function startWebSite(books) {
    $(document).ready(function() {
        setTimeout(function(){
            // Loading the carousel
            $('.my-flipster').flipster({
                style: 'flat',
                buttons: true,
                spacing: 0.7,
                itemContainer: '#itemContainer',
                itemSelector: '.itemSelector',
                loop: true,
                onItemSwitch: function (currentItem, previousItem) {
                    onItemSwitch(currentItem);
                }
            }).ready(function(){

                // Loading the description
                $('.my-flipster #itemContainer').hover(function() {
                    $('.my-flipster').bind('mousewheel DOMMouseScroll',function(e){
                        stopWheel(e);
                    });
                }, function() {
                    $('.my-flipster').unbind('mousewheel DOMMouseScroll');
                });
                setTimeout(function(){
                    var index = $('.flipster__item--current').index();
                    fillDescription(books, index);
                    $('body').addClass('loaded');
                }, 1000);
            });
        }, 5500);
    });
}



/**
 * Fill in the description fields under the carousel.
 * */
function fillDescription(books, index) {
    if (books.length !== 0) {
        $('#descriptionTitle').text(books[index].title);
        $('#author').text("by " + books[index].author);
        $('#mark').text(convertMarkInStars(books[index].mark));
    }
}



/**
 * Function called when moving in the carousel.
 * */
function onItemSwitch(currentItem) {
    var description = $('#description');
    var index = getIndex(currentItem);

    description.fadeOut(0.5);
    fillDescription(books, index);
    description.fadeIn();
}



/**
 * Loads the details of the selected book and displays them below.
 * */
function getBookDetailView(reference) {
    var url = '/book-detail/' + reference;
    $('.flipster__item .book').removeClass("opened-book");
    $('.flipster__item--current .book').addClass("opened-book");

    $('#bookDetailBlock').load(url).ready(function () {
        document.documentElement.style.overflowY = "visible";
        document.body.style.overflowY = "visible";

        $('.flipster__item--current').removeAttr("onclick");
        $('.flipster__item').attr("onclick", "getBookDetailView(this.id)");
        if ($('.fa-chevron-down').length === 0) {
            $('#carousel').append("<i class=\"fas fa-chevron-down\"></i>");
        }
    });
}



/**
 * Retrieves the index of the selected book from the carousel.
 * */
function getIndex(el) {
    return Array.from(el.parentNode.children).indexOf(el)
}



/**
 * Prevents vertical scrolling of the page.
 * */
function stopWheel(e){
    if(!e){ /* IE7, IE8, Chrome, Safari */
        e = window.event;
    }
    if(e.preventDefault) { /* Chrome, Safari, Firefox */
        e.preventDefault();
    }
    e.returnValue = false; /* IE7, IE8 */
}



/**
 * Transforms the numerical note into a star number.
 * */
function convertMarkInStars(mark) {
    var markInStars = "";
    for(var i = 0; i < mark; i++){
        markInStars += "★"
    }
    if (markInStars.length < 5) {
        var emptyStars = 5 - markInStars.length;
        for(i = 0; i < emptyStars; i++){
            markInStars += "☆"
        }
    }
    return markInStars;
}



/**
 * Animates the transition to the user settings page.
 * */
function showParameter() {
    body = $('body');
    body.removeClass('loaded');
    body.addClass('unloaded');
    setTimeout(function () {
        window.location.href = "/user/parameter";
    }, 800)
}