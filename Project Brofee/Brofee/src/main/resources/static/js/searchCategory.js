$(document).ready(function() {
    $('#categoryName').keyup(function() {
        $('#resultCategory').html('');
        var search = $('#categoryName').val();
        if (search !== '') {
            $('#resultCategory').css('display', 'inherit');
            var expression = new RegExp(search, 'i');
            $.getJSON('/json/Category.json', function(data) {
                var resultsFound = false;
                $.each(data, function(key, value) {
                    if (value.nameCategory.search(expression) !== -1) {
                        resultsFound = true;
                        $('#resultCategory').append(
                            '<li data-category-id="' + value.id + '" style="cursor:pointer; display: flex; max-height: 140px;" class="list-group-item link-class">' +
                            '<div style="padding-left:10px; flex-direction: column; margin-left: 2px;"><span width="100%" style="font-size:16px;font-weight: 700;">' +
                            value.nameCategory +
                            '</span></div></li>');
                    }
                });

                if (!resultsFound) {
                    $('#resultCategory').append(
                        '<li class="list-group-item">Không tìm thấy kết quả</li>'
                    );
                }
            });
        } else {
            $('#resultCategory').css('display', 'none');
        }
    });

    $('#resultCategory').on('click', 'li', function() {
        var categoryId = $(this).data('category-id');
        var categoryTitle = $(this).find('span').text();
        $('#selectedCategoryId').val(categoryId);
        $('#categoryName').val(categoryTitle);
        $('#resultCategory').html('').css('display', 'none');
    });
});