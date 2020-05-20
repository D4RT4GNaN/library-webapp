function lineclamp() {
    var synopsis = $('#synopsis');
    var lineHeight = parseFloat(synopsis.css('line-height'));
    var pageHeight = $('#content').height();
    var calc = parseInt(pageHeight / lineHeight) - 1;
    document.documentElement.style.setProperty('--line-clamp', calc);
}

$(document).ready(function() {
    lineclamp();
});

$( window ).resize(function() {
    lineclamp();
});