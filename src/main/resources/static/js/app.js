const main = {
    init : function() {
        const _this = this;

        // 게시글
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        // 회원
        $('#btn-join').on('click', function () {
            _this.join();
        });

        $('#btn-login').on('click', function () {
            _this.login();
        })
    },

    /* 게시글 */
    save : function () {
        const data = {
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val()
        };
        // 공백 및 빈 문자열 체크
        if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "" ||
            !data.writer || data.writer.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/api/posts',
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    update : function () {
        const data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        const id = $('#id').val();
        const con_check = confirm("수정하시겠습니까?");
        if (con_check == true) {
            if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
                alert("공백 또는 입력하지 않은 부분이 있습니다.");
                return false;
            } else {
                $.ajax({
                    type: 'PUT',
                    url: '/api/posts/' + id,
                    dataType: 'JSON',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function () {
                    alert("수정되었습니다.");
                    window.location.href = '/posts/read/' + id;
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
        } else {
            return false;
        }
    },

    delete : function () {
        const id = $('#id').val();
        const con_check = confirm("정말 삭제하시겠습니까?");

        if(con_check == true) {
            $.ajax({
                type: 'DELETE',
                url: '/api/posts/'+id,
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8'

            }).done(function () {
                alert("삭제되었습니다.");
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        } else {
            return false;
        }
    },

    /* 사용자 */
    join : function () {
        // alert("user의 userSave 호출됨");
        const data = {
            username: $('#username').val(),
            nickname: $('#nickname').val(),
            password: $('#password').val(),
            email: $('#email').val()
        }
        // console.log(data);

        $.ajax({
            type: "POST",
            url: "/api/join",
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(function () {
            alert("회원가입이 완료되었습니다.");
            window.location.href = "/user/login";

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    login : function () {
        const data = {
            username: $('#username').val(),
            password: $('#password').val()
        }

        $.ajax({
            type: "POST",
            url: "/api/login",
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("[ " + data.username + " ] 님 환영합니다.");
            window.location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })

    }
};

main.init();