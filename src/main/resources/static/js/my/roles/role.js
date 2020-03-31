function initRoles() {
	$.ajax({
		type : 'get',
		url : '/roles/all',
		async : false,
		success : function(data) {
			var r = $("#roles");

			for (var i = 0; i < data.length; i++) {
				var d = data[i];
				var id = d['id'];
				var name = d['name'];

				var t = "<label><input type='checkbox' value='" + id + "'>"
						+ name + "</label> &nbsp&nbsp";

				r.append(t);
			}
		}
	});
}
function initRolesAddUser() {
    $.ajax({
        type : 'get',
        url : '/roles/all',
        async : false,
        success : function(data) {
            var r = $("#rolesTable");
			console.log((Math.ceil(data.length/2)));
			var  j = Math.ceil(data.length/2);
			var l ="";
            var p = 0 ;
			for(var k = 0; k<j ;k++){
				l += "<tr><td style='width: 70%;' id='roleTd"+p+"'></td><td id='roleTd"+(p+1)+"'></td></tr>";
				p=p+2;
			}
			r.append(l);
            for (var i = 0; i < data.length; i++) {
                var d = data[i];
                var id = d['id'];
                var name = d['name'];
                var t = "<label style='color: #fff;'><input type='checkbox' value='" + id + "'>"
                    + name + "</label>";
                $("#roleTd"+i).append(t);
            }
        }
    });
}

function getCheckedRoleIds() {
	var ids = [];
	$("#roles input[type='checkbox']").each(function() {
		if ($(this).prop("checked")) {
			ids.push($(this).val());
		}
	});

	return ids;
}

function initRoleDatas(userId) {
	$.ajax({
		type : 'get',
		url : '/roles?userId=' + userId,
		success : function(data) {
			var length = data.length;
			for (var i = 0; i < length; i++) {
				$("input[type='checkbox']").each(function() {
					var v = $(this).val();
					if (v == data[i]['id']) {
						$(this).attr("checked", true);
					}
				});
			}
		}
	});
}