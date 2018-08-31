$(document).ready(function () {
    let partsContainer = $('#partsContainer');
    let partsInput = $('#partsInput');

    $('.partSelect').change(function (e) {
        let select = $(e.target);

        partsContainer.append(
            $('<input class="btn btn-primary m-2" type="button" value="' + select.val() +'"/>')
                .click(function () {
                    $(this).remove();
                    let partName = $(this).val();
                    partsInput.val(partsInput.val().replace(partName + ', ', ''))
            })
        );

        let selectedOption = select.find('option:selected');
        partsInput.val(partsInput.val() + selectedOption.val() + ', ');
        select.find('option:selected').removeAttr('selected');
    });
});