window.onhashchange = function () {
    choose(window.location.href.toString());
}

function choose(url) {
    var f = url.split("/").slice(-1)[0].split("?")[0];
    if (f.match(/#/g) && f.match(/#/g).length > 0) {
        f = f.split("#");
    }
    $('div.non-sidebar').empty();
    if (f[0] == "models") {
        $('div.non-sidebar').load("models/" + f[1] + ".html", function () {
            applyMarkup();
            goToAnchor();
        });
    } else {
        if (f.length > 2) {
            $('div.non-sidebar').load("operations/" + f[1] + ".html", function () {
                applyMarkup();
                goToAnchor();
            });
        } else {
            $('div.non-sidebar').load(f[1] + ".html", function () {
                applyMarkup();
            });
        }
    }
}

function goToAnchor() {
    var anchorArr = window.location.href.toString().split("#");
    if (anchorArr.length > 2) {
        var anchor = anchorArr[anchorArr.length - 1];
        window.scrollTo(0, $('a[name=' + anchor + ']').offset().top - 80);
    }
}

function resize() {
    //$(".sidebar").css('height', $(window).height() - 60);
    //$("#content-window").css('height', $(window).height() - 60);
}

function applyMarkup() {
    $('.marked').each(function () {
        var text = $(this).text();
        text = text.replace(new RegExp('(\\\\n)', 'g'), " ");
        text = text.replace(new RegExp('(\\\\")', 'g'), "");
        $(this).html(marked(text));
    });
}

$(function () {
    window.onresize = resize;
    resize();
    applyMarkup();

    $(window).bind('hashchange', function () {
        choose(window.location.href.toString());
    });

    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
});
