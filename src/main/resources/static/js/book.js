function lineclamp() {
    let synopsis = $('#synopsis');
    let lineHeight = parseFloat(synopsis.css('line-height'));
    let pageHeight = $('#content').height();
    let calc = parseInt(pageHeight / lineHeight) - 1;
    synopsis.css({"-webkit-line-clamp": "" + calc + ""});
}

$(document).ready(function() {
    lineclamp();
});

$( window ).resize(function() {
    lineclamp();
});