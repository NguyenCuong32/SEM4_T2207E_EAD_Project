//Toggle user menu
$(document).ready(function() {
    $("#user-avatar-btn").on("click", function(event) {
        $("#user-menu").toggleClass("active");
        event.stopPropagation(); // Prevent the click event from bubbling up
    });

    $(document).on("click", function(event) {
        if (!$(event.target).closest("#user-menu").length && !$(event.target).closest("#user-avatar-btn").length) {
            $("#user-menu").removeClass("active");
        }
    });
});

//Toggle user menu 2
$(document).ready(function() {
    $("#user-avatar-btn-2").on("click", function(event) {
        $("#user-menu-2").toggleClass("active");
        event.stopPropagation(); // Prevent the click event from bubbling up
    });

    $(document).on("click", function(event) {
        if (!$(event.target).closest("#user-menu-2").length && !$(event.target).closest("#user-avatar-btn-2").length) {
            $("#user-menu-2").removeClass("active");
        }
    });
});