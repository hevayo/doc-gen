+$(function () {
    /**
     * Set correct page specified by hash url on content pane
     */
    var setPage = (function choose() {
        var url = window.location.href.toString();
        var hashParts = splitURL(url);
        var path = "";

        if (hashParts == "index.html") {
            formatToHTML();
            return;
        }
        $('div.non-sidebar').empty();
        if (hashParts[0] == "models") {
            path = "models/" + hashParts[1] + ".html";
        } else {
            if (hashParts.length > 2) {
                path = "operations/" + hashParts[1] + ".html";
            } else {
                path = hashParts[1] + ".html";
            }
        }

        loadPage(path , hashParts[2]);
        return choose;
    })();

    /**
     * Request content page for a given path and set content on content pane.
     * @param path path to the page to load
     * @param anchor anchor to scroll after loading the page
     */
    function loadPage(path, anchor) {
        $('div.non-sidebar').load(path, function () {
            formatToHTML();
            goToAnchor(anchor);
        });
    }

    /**
     * Scroll to selected anchor within the page loaded.
     * @param anchor name attribute of the anchor.
     */
    function goToAnchor(anchor) {
        if (anchor) {
            window.scrollTo(0, $('a[name=' + anchor + ']').offset().top - 80);
        }
    }

    /**
     * Format elements marked by css class 'marked'.
     * Escaped '\n' and '\"' characters will be escaped.
     * Markdown formatting will be applied.
     */
    function formatToHTML() {
        $('.marked').each(function () {
            var text = $(this).text();
            text = text.replace(new RegExp('(\\\\n)', 'g'), " ");
            text = text.replace(new RegExp('(\\\\")', 'g'), "");
            $(this).html(marked(text));
        });
    }

    /**
     * Split url to get hash components of the url
     * @param url original url to split
     * @returns {String} extracted hash components of the url
     */
    function splitURL(url) {
        var parts = url.split("/").slice(-1)[0].split("?")[0];
        var match = parts.match(/#/g);

        if (match && match.length > 0) {
            parts = parts.split("#");
        }
        return parts;
    }

    // event listeners
    window.onhashchange = setPage;
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
});
