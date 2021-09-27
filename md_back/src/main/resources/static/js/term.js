let term = {
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

        $("#btn-deleteDB").on("click",()=>{
            this.deleteDB();
        });
    },

    save: function () {
        let data = {
            shortName: $("#shortName").val(),
            engName: $("#engName").val(),
            korName: $("#korName").val(),
            meaning: $("#meaning").val(),
            banWord: $("#banWord").val()
            // merge words
        };

        $.ajax({
            type: "POST",
            url: "/term",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf8",
            dataType: "json"
        }).done(function (rsp) {
            if (rsp == "engNullErr") {
                alert("영문명을 입력해주세요.");
                location.href = "#engArea";
            } else {
                alert("용어 생성이 완료되었습니다.");
                location.href = "/term";
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
            url: "/term/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf8",
            dataType: "json"
        }).done(function (rsp) {
            if (rsp == "engNullErr") {
                alert("영문명을 입력해주세요.");
                location.href = "#engArea";
            } else {
                alert("용어 수정이 완료되었습니다.");
                location.href = "/term/" + id;
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
            url: "/term/" + id,
            dataType: "json"
        }).done(function (rsp) {
            alert("용어 삭제가 완료되었습니다.");
            location.reload();
        }).fail(function (fail) {
                alert(fail);
            }
        )
    },

    deleteDB: function () {
        let id = $("#id").val();
        $.ajax({
            type: "DELETE",
            url: "/term/" + id + "/deleteDB",
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

term.init();