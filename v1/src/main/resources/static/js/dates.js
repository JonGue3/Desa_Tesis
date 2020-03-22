//Funcion que activa el calendario de inicio por Id
function dateInput(dateId, dateIdEnd) {
    if ($("#" + dateIdEnd).length > 0) {
        $("#" + dateIdEnd).datepicker('destroy');
    }
    date = new Date();
    var year1920 = date.getFullYear() - 1920;
    var currentyear = date.getFullYear();
    date.setDate(date.getDate());
    date.setMonth(0);
    date.setFullYear(currentyear - year1920);
    $("#" + dateId).datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        minDate: date
    });
};


//Funcion que activa el calendario  de fin por Id
function dateInputEnd(dateIdStart, dateIdEnd) {
    var $inputStart = $('#' + dateIdStart);
    var $inputEnd = $('#' + dateIdEnd);
    date = new Date();
    var year1920 = date.getFullYear() - 1920;
    var currentyear = date.getFullYear();
    date.setDate(date.getDate());
    date.setMonth(0);
    date.setFullYear(currentyear - year1920);
    var specificField = $($inputStart).parsley();
    var startDateValue = $inputStart.val();
    window.ParsleyUI.removeError(specificField, "startDateEmpty");
    if ($inputStart.val() == "") {
        window.ParsleyUI.addError(specificField, "startDateEmpty", 'Debe Colocar una fecha de inicio');
    } else {
        $("#" + dateIdEnd).datepicker({
            dateFormat: "dd/mm/yy",
            changeMonth: true,
            changeYear: true,
            minDate: startDateValue

        });
    }
};

// Funcion  que   valida campos para cargar loader
function validarLoader(idForm) {
    debugger;
    var $form = $('#'+idForm);
    var validarParley = $form.parsley().validate();
    //Condicion que valida si hay campos vacios  carga o no carga el loader
    if (validarParley) {
        loader();
    }
//---------------------------------------------------------------------------//
}