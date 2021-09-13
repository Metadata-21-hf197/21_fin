let domain = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

        $("#btn-delete").on("click", () => {
            this.delete();
        });

        $("#btn-codeSave").on("click", () => {
            this.codeSave();
        });

        $("#btn-codeUpdate").on("click", () => {
            this.codeUpdate();
        });

        $("#btn-codeDelete").on("click", () => {
            this.codeDelete();
        });

        $("#btn-deleteDB").on("click", () => {
            this.deleteDB();
        });

    },

    save: function () {
        let data = {
            shortName: $("#shortName").val(),
            engName: $("#engName").val(),
            korName: $("#korName").val(),
            meaning: $("#meaning").val(),
            type: $("#type").val(),
            banWord: $("#banWord").val()
        };

        $.ajax({
            type: "POST",
            url: "/domain/insert",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf8",
            dataType: "json"
        }).done(function (rsp) {
            if (rsp == "engErr") {
                alert("영문명을 입력해주세요.");
                document.getElementById("#engArea").focus();
            } else {
                alert("단어 생성이 완료되었습니다.");
                window.location = "/domain";
            }
        }).fail(function (fail) {
                alert(fail);
            }
        )
    },

    update: function () {
        let id = $("#id").val();
        let data = {
            shortName: $("#shortName").val(),
            engName: $("#engName").val(),
            korName: $("#korName").val(),
            meaning: $("#meaning").val(),
            banWord: $("#banWord").val()
        };

        $.ajax({
            type: "PUT",
            url: "/domain/" + id + "/update",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf8",
            dataType: "json"
        }).done(function (rsp) {
            if (rsp == "engErr") {
                alert("영문명을 입력해주세요.");
                document.getElementById("#engArea").focus();
            } else {
                alert("단어 수정이 완료되었습니다.");
                window.location = "/domain";
            }
        }).fail(function (fail) {
                alert(fail);
            }
        )
    },
    delete: function () {
        let id = $("#id").val();
        $.ajax({
            type: "DELETE",
            url: "/domain/" + id + "/delete",
            dataType: "json"
        }).done(function (rsp) {
            alert("단어 생성이 완료되었습니다.");
            window.location = "/domain";
        }).fail(function (fail) {
                alert(fail);
            }
        )
    },
    codeSave: function () {
        let id = $("#id").val();
        let data = {
            shortName: $("#shortName").val(),
            engName: $("#engName").val(),
            korName: $("#korName").val(),
        };

        $.ajax({
            type: "POST",
            url: "/domain/" + id + "/addCode",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf8",
            dataType: "json"
        }).done(function (rsp) {
            if (rsp == "engErr") {
                alert("영문명을 입력해주세요.");
                document.getElementById("#engArea").focus();
            } else {
                alert("코드 생성이 완료되었습니다.");
                window.location = "/domain";
            }
        }).fail(function (fail) {
                alert(fail);
            }
        )
    },
    codeUpdate: function () {
        let id = $("#id").val();
        let codeId = $("#codeId").val();
        let data = {
            shortName: $("#shortName").val(),
            engName: $("#engName").val(),
            korName: $("#korName").val(),
        };

        $.ajax({
            type: "PUT",
            url: "/domain/" + id + "/addCode/" + codeId,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf8",
            dataType: "json"
        }).done(function (rsp) {
            if (rsp == "engErr") {
                alert("영문명을 입력해주세요.");
                document.getElementById("#engArea").focus();
            } else {
                alert("코드 수정이 완료되었습니다.");
                window.location = "/domain";
            }
        }).fail(function (fail) {
                alert(fail);
            }
        )
    },
    codeDelete: function () {
        let id = $("#id").val();
        let codeId = $("#codeId").val();
        $.ajax({
            type: "DELETE",
            url: "/domain/" + id + "/delete/" + codeId,
            dataType: "json"
        }).done(function (rsp) {
            alert("단어 삭제가 완료되었습니다.");
            window.location = "/domain";
        }).fail(function (fail) {
                alert(fail);
            }
        )
    },

    deleteDB: function () {
        let id = $("#id").val();
        $.ajax({
            type: "DELETE",
            url: "/domain/" + id + "/deleteDB",
            dataType: "json"
        }).done(function (rsp) {
            alert(id + ":delete on db");
            location.reload();
        }).fail(function (fail) {
                alert(fail);
            }
        )
    }

}

domain.init();