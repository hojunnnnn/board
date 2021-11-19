const main = {
    init : function() {
        const _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
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

        const id =$('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/posts/'+id,
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('수정되었습니다.');
            window.location.href = '/posts/read/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();