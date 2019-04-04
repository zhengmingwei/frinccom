import Network from 'tigerfacejs-network';
export const RECEIVE_AUDIT_LIST = 'RECEIVE_AUDIT_LIST';

export function firstAccept(audit) {
    return dispatch => {
        E.doAdd("manager/audit/firstAccept", audit);

        E.addOneTimeEventListener("firstAccept", function (e) {
            if(e.data.returnCode == "200"){
                E.dispatchEvent("toCommodityList6");
            } else {
                alert("保存失败");// alert(e.data.message);
            }

        });
    };
}
export function firstReject(audit) {
    return dispatch => {
        E.doAdd("manager/audit/firstReject", audit);

        E.addOneTimeEventListener("firstReject", function (e) {
            if(e.data.returnCode == "200"){
                E.dispatchEvent("toCommodityList7");
            } else {
                alert("保存失败");// alert(e.data.message);
            }

        });
    };
}

export function reAccept(audit) {
    return dispatch => {
        E.doAdd("manager/audit/reAccept", audit);

        E.addOneTimeEventListener("reAccept", function (e) {
            if(e.data.returnCode == "200"){
                E.dispatchEvent("toCommodityList9");
            } else {
                alert("保存失败");// alert(e.data.message);
            }

        });
    };
}
export function reReject(audit) {
    return dispatch => {
        E.doAdd("manager/audit/reReject", audit);

        E.addOneTimeEventListener("reReject", function (e) {
            if(e.data.returnCode == "200"){
                E.dispatchEvent("toCommodityList10");
            } else {
                alert("保存失败");// alert(e.data.message);
            }

        });
    };
}
export function finalAccept(audit) {
    return dispatch => {
        E.doAdd("manager/audit/finalAccept", audit);

        E.addOneTimeEventListener("finalAccept", function (e) {
            if(e.data.returnCode == "200"){
                E.dispatchEvent("toCommodityList14");
            } else {
                alert("保存失败");// alert(e.data.message);
            }

        });
    };
}
export function finalReject(audit) {
    return dispatch => {
        E.doAdd("manager/audit/finalReject", audit);

        E.addOneTimeEventListener("finalReject", function (e) {
            if(e.data.returnCode == "200"){
                E.dispatchEvent("toCommodityList11");
            } else {
                alert("保存失败");// alert(e.data.message);
            }

        });
    };
}

export function findByCommodityId(commodityId) {
    return dispatch => {
        E.doAdd("manager/audit/findAudits?commodityId="+ commodityId);

        E.addOneTimeEventListener("findAudits", function (e) {
            if(e.data.returnCode == "200"){
                dispatch(receiveAudits(e.data.result));
            } else {
                alert("保存失败");// alert(e.data.message);
            }

        });
    };
}

export function receiveAudits(data) {
    return{
        type: RECEIVE_AUDIT_LIST,
        data: data
    }

}