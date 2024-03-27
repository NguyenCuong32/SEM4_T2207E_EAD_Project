/*

Template: LuxurySpa - Spa & Beauty HTML Template
Author: potenzaglobalsolutions
Design and Developed by: potenzaglobalsolutions.com

NOTE: This file contains all scripts for the actual Template.

*/

/*================================================
[  Table of contents  ]
================================================

:: Preloader
:: Menu
:: Sticky
:: Tooltip
:: Popover
:: Counter
:: Owl carousel
:: Slick slider
:: Datetime picker
:: Select2
:: Range Slider
:: Countdown
:: Swiper Animation
:: Back to top
:: Magnific Popup
:: Search bar

======================================
[ End table content ]
======================================*/
//POTENZA var

(function ($) {
  "use strict";
  var POTENZA = {};

  /*************************
  Predefined Variables
*************************/
  var $window = $(window),
    $document = $(document),
    $body = $("body"),
    $countdownTimer = $(".countdown"),
    $counter = $(".counter");
  //Check if function exists
  $.fn.exists = function () {
    return this.length > 0;
  };

  /*************************
        Preloader
  *************************/
  POTENZA.preloader = function () {
    $("#load").fadeOut();
    $("#pre-loader").delay(0).fadeOut("slow");
  };

  /*************************
      Menu
  *************************/
  POTENZA.dropdownmenu = function () {
    if ($(".navbar").exists()) {
      $(".dropdown-menu a.dropdown-toggle").on("click", function (e) {
        if (!$(this).next().hasClass("show")) {
          $(this)
            .parents(".dropdown-menu")
            .first()
            .find(".show")
            .removeClass("show");
        }
        var $subMenu = $(this).next(".dropdown-menu");
        $subMenu.toggleClass("show");
        $(this)
          .parents("li.nav-item.dropdown.show")
          .on("hidden.bs.dropdown", function (e) {
            $(".dropdown-submenu .show").removeClass("show");
          });
        return false;
      });
    }
  };

  /*************************
         Sticky
*************************/

  POTENZA.isSticky = function () {
    $(window).scroll(function () {
      if ($(this).scrollTop() > 450) {
        $(".header-sticky").addClass("is-sticky");
      } else {
        $(".header-sticky").removeClass("is-sticky");
      }
    });
  };

  /*************************
       Tooltip
  *************************/
  POTENZA.Tooltip = function () {
    var tooltipTriggerList = [].slice.call(
      document.querySelectorAll('[data-bs-toggle="tooltip"]')
    );
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
      return new bootstrap.Tooltip(tooltipTriggerEl);
    });
  };

  /*************************
        Popover
  *************************/
  POTENZA.Popover = function () {
    var popoverTriggerList = [].slice.call(
      document.querySelectorAll('[data-bs-toggle="popover"]')
    );
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
      return new bootstrap.Popover(popoverTriggerEl);
    });
  };

  /*************************
       Counter
*************************/
  POTENZA.counters = function () {
    var counter = jQuery(".counter");
    if (counter.length > 0) {
      $counter.each(function () {
        var $elem = $(this);
        $elem.appear(function () {
          $elem.find(".timer").countTo();
        });
      });
    }
  };

  /*************************
       Owl carousel
*************************/
  POTENZA.carousel = function () {
    var owlslider = jQuery("div.owl-carousel");
    if (owlslider.length > 0) {
      owlslider.each(function () {
        var $this = $(this),
          $items = $this.data("items") ? $this.data("items") : 1,
          $loop = $this.attr("data-loop") ? $this.data("loop") : true,
          $navdots = $this.data("nav-dots") ? $this.data("nav-dots") : false,
          $navarrow = $this.data("nav-arrow") ? $this.data("nav-arrow") : false,
          $autoplay = $this.attr("data-autoplay")
            ? $this.data("autoplay")
            : true,
          $autospeed = $this.attr("data-autospeed")
            ? $this.data("autospeed")
            : 5000,
          $smartspeed = $this.attr("data-smartspeed")
            ? $this.data("smartspeed")
            : 1000,
          $autohgt = $this.data("autoheight")
            ? $this.data("autoheight")
            : false,
          $space = $this.attr("data-space") ? $this.data("space") : 30,
          $animateOut = $this.attr("data-animateOut")
            ? $this.data("animateOut")
            : false;
        $(this).owlCarousel({
          loop: $loop,
          items: $items,
          responsive: {
            0: {
              items: $this.data("xx-items") ? $this.data("xx-items") : 1,
            },
            480: {
              items: $this.data("xs-items") ? $this.data("xs-items") : 1,
            },
            768: {
              items: $this.data("sm-items") ? $this.data("sm-items") : 2,
            },
            980: {
              items: $this.data("md-items") ? $this.data("md-items") : 3,
            },
            1200: {
              items: $items,
            },
          },
          dots: $navdots,
          autoplayTimeout: $autospeed,
          smartSpeed: $smartspeed,
          autoHeight: $autohgt,
          margin: $space,
          nav: $navarrow,
          navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>",
          ],
          autoplay: $autoplay,
          autoplayHoverPause: true,
        });
      });
    }
  };

  /*************************
        Slick slider
*************************/
  POTENZA.slickslider = function () {
    if ($(".slider-for").exists()) {
      $(".slider-for").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: true,
        asNavFor: ".slider-nav",
      });
      $(".slider-nav").slick({
        slidesToShow: 3,
        slidesToScroll: 1,
        asNavFor: ".slider-for",
        dots: false,
        centerMode: true,
        focusOnSelect: true,
      });
    }
  };

  /*************************
      datetime picker
  *************************/
  POTENZA.datetimepickers = function () {
    if ($(".datetimepickers").exists()) {
      $("#datetimepicker-01, #datetimepicker-02").datetimepicker({
        format: "L",
      });
      $("#datetimepicker-03, #datetimepicker-04").datetimepicker({
        format: "LT",
      });
    }
  };

  /*************************
      Select2
  *************************/
  POTENZA.select2 = function () {
    if ($(".basic-select").exists()) {
      var select = jQuery(".basic-select");
      if (select.length > 0) {
        $(".basic-select").select2({ dropdownCssClass: "bigdrop" });
      }
    }
  };

  /*************************
      Range Slider
*************************/
  POTENZA.rangesliders = function () {
    if ($(".property-price-slider").exists()) {
      var rangeslider = jQuery(".rangeslider-wrapper");
      $("#property-price-slider").ionRangeSlider({
        type: "double",
        min: 0,
        max: 10000,
        from: 1000,
        to: 8000,
        prefix: "$",
        hide_min_max: true,
        hide_from_to: false,
      });
    }
  };

  /*************************
           Countdown
  *************************/
  POTENZA.countdownTimer = function () {
    if ($countdownTimer.exists()) {
      $countdownTimer.downCount({
        date: "12/31/2024 12:00:00", // Month/Date/Year HH:MM:SS
        offset: -4,
      });
    }
  };

  /*************************
   Swiper Animation
*************************/
  POTENZA.swiperAnimation = function () {
    var siperslider = jQuery(".swiper-container");
    if (siperslider.length > 0) {
      var swiperAnimation = new SwiperAnimation();
      var swiper = new Swiper(".swiper-container", {
        init: true,
        direction: "horizontal",
        effect: "slide",
        loop: true,

        keyboard: {
          enabled: true,
          onlyInViewport: true,
        },
        pagination: {
          el: ".swiper-pagination",
          dynamicBullets: true,
          clickable: true,
        },
        // Navigation arrows
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
        on: {
          init: function () {
            swiperAnimation.init(this).animate();
          },
          slideChange: function () {
            swiperAnimation.init(this).animate();
          },
        },
      });
    }
  };

  /*************************
     Back to top
*************************/
  POTENZA.goToTop = function () {
    var $goToTop = $("#back-to-top");
    $goToTop.hide();
    $window.scroll(function () {
      if ($window.scrollTop() > 100) $goToTop.fadeIn();
      else $goToTop.fadeOut();
    });
    $goToTop.on("click", function () {
      $("body,html").animate(
        {
          scrollTop: 0,
        },
        1000
      );
      return false;
    });
  };

  /*************************
     Search bar
*************************/
  POTENZA.searchbar = function () {
    if ($("#search").exists()) {
      $('a[href="#search"]').on("click", function (event) {
        event.preventDefault();
        $("#search").addClass("open");
        $('#search > form > input[type="search"]').focus();
      });
      $("#search, #search button.close").on("click keyup", function (event) {
        if (
          event.target == this ||
          event.target.className == "close" ||
          event.keyCode == 27
        ) {
          $(this).removeClass("open");
        }
      });
      //Do not include! This prevents the form from submitting for DEMO purposes only!
      $("form").submit(function (event) {
        event.preventDefault();
        return false;
      });
    }
  };

  /*************************
      Magnific Popup
  *************************/
  POTENZA.mediaPopups = function () {
    if (
      $(".popup-single").exists() ||
      $(".popup-gallery").exists() ||
      $(".modal-onload").exists() ||
      $(".popup-youtube, .popup-vimeo, .popup-gmaps").exists()
    ) {
      if ($(".popup-single").exists()) {
        $(".popup-single").magnificPopup({
          type: "image",
        });
      }
      if ($(".popup-gallery").exists()) {
        $(".popup-gallery").magnificPopup({
          delegate: "a.portfolio-img",
          type: "image",
          tLoading: "Loading image #%curr%...",
          mainClass: "mfp-img-mobile",
          gallery: {
            enabled: true,
            navigateByImgClick: true,
            preload: [0, 1], // Will preload 0 - before current, and 1 after the current image
          },
        });
      }
      if ($(".popup-youtube, .popup-vimeo, .popup-gmaps").exists()) {
        $(".popup-youtube, .popup-vimeo, .popup-gmaps").magnificPopup({
          disableOn: 700,
          type: "iframe",
          mainClass: "mfp-fade",
          removalDelay: 160,
          preloader: false,
          fixedContentPos: false,
        });
      }
      var $modal = $(".modal-onload");
      if ($modal.length > 0) {
        $(".popup-modal").magnificPopup({
          type: "inline",
        });
        $(document).on("click", ".popup-modal-dismiss", function (e) {
          e.preventDefault();
          $.magnificPopup.close();
        });
        var elementTarget = $modal.attr("data-target");
        setTimeout(function () {
          $.magnificPopup.open(
            {
              items: {
                src: elementTarget,
              },
              type: "inline",
              mainClass: "mfp-no-margins mfp-fade",
              closeBtnInside: !0,
              fixedContentPos: !0,
              removalDelay: 500,
            },
            0
          );
        }, 1500);
      }
    }
  };

  /*************************
      Animated circle text
  *************************/
  POTENZA.animatedcircletext = function () {
    const text = document.querySelector(".text");
    text.innerHTML = text.innerText
      .split("")
      .map(
        (char, i) =>
          `<span style="transform:rotate(${i * 11.3}deg)">${char}</span>`
      )
      .join("");
  };

  /*************************
     Qty
    *************************/
  POTENZA.qty = function () {
    $(document).ready(function () {
      $(".minus").click(function () {
        var $input = $(this).parent().find("input");
        var count = parseInt($input.val()) - 1;
        count = count < 1 ? 1 : count;
        $input.val(count);
        $input.change();
        return false;
      });
      $(".plus").click(function () {
        var $input = $(this).parent().find("input");
        $input.val(parseInt($input.val()) + 1);
        $input.change();
        return false;
      });
    });
  };

  /****************************************************
       POTENZA Window load and functions
  ****************************************************/
  //Window load functions
  $window.on("load", function () {
    POTENZA.preloader();
  });
  //Document ready functions
  $document.ready(function () {
    POTENZA.counters(),
      POTENZA.slickslider(),
      POTENZA.datetimepickers(),
      POTENZA.select2(),
      POTENZA.dropdownmenu(),
      POTENZA.isSticky(),
      POTENZA.goToTop(),
      POTENZA.countdownTimer(),
      POTENZA.rangesliders(),
      POTENZA.Tooltip(),
      POTENZA.Popover(),
      POTENZA.swiperAnimation(),
      POTENZA.searchbar(),
      POTENZA.mediaPopups(),
      POTENZA.carousel(),
      POTENZA.animatedcircletext(),
      POTENZA.qty();
  });
})(jQuery);
