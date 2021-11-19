const main = {
    init : function() {
        const _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },

    save : function () {
        const data = {
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val()
        };

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
    },

    update : function () {
        const data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        const id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/posts/'+id,
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            const con_check = confirm("수정하시겠습니까?");
            if (con_check == true) {
                window.location.href = '/posts/read/' + id;
            } else {
                return ;
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        const id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/posts/'+id,
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            const con_check = confirm("삭제하시겠습니까?");
            if (con_check == true) {
                window.location.href = '/';
            } else {
                return ;
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();