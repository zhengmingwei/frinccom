
import Network from 'tigerfacejs-network';

export const RECEIVE_PAYMENT_LIST = 'RECEIVE_PAYMENT_LIST';
export const PAYMENT_CRITERIA_CHANGED = 'PAYMENT_CRITERIA_CHANGED';
export const PAYMENT_STATUS_CHANGED = 'PAYMENT_STATUS_CHANGED';
export const SELECT_PAYMENT = 'SELECT_PAYMENT';

export function receivePaymentList(data) {
    return {
        type: RECEIVE_PAYMENT_LIST,
        data: data
    }
}

export function refreshPaymentList(criteria={}, page={}) {
    return dispatch => {
        E.doFind("manager/payment/query",Object.assign({}, criteria, page));
        E.addOneTimeEventListener("paymentList", function (e) {
            dispatch(receivePaymentList(e.data));
        });
    };
};

export function selectPayment(data) {
    return {
        type: SELECT_PAYMENT,
        data: data
    }
}
export function paymentCriteriaChanged(data) {
    return {
        type: PAYMENT_CRITERIA_CHANGED,
        data: data
    }
};

export function savePayment(payment) {
    return dispatch => {
        console.log(payment);
        E.doAdd("manager/payment", payment);

        E.addOneTimeEventListener("payment", function (e) {
            if(e.data.returnCode == "200"){
                E.dispatchEvent("topaymentlist");
            } else {
                alert("保存失败");// alert(e.data.message);
            }
            // dispatch(selectDistributor(e.data));
            // dispatch(refreshcommodityList());
        });
    };
}

export function getPayment(id) {
    console.log("idididid",id);
    if(id){
        return dispatch => {
            E.doFind("/manager/payment/"+id);
            E.addOneTimeEventListener("payment", function (e) {
                if(e.data.returnCode == "200") {
                    const payment = e.data.result;
                    dispatch(selectPayment(payment));

                } else {
                    console.log("查询失败")
                }
            });
        }
    } else {
        return dispatch => {
            const payment = { id:null, transferDate: "", payer:"", fee:0, memo:"", commodityId:"", distributorId:"", commdity:{}}
            dispatch(selectPayment(payment));

        }
    }
}

export function delPayment(paymentId, criteria={}, page={}) {
    return dispatch => {
        E.doRemove("manager/payment/"+ paymentId);
        console.warn(paymentId)
        E.addOneTimeEventListener("paymentDeleted", function (e) {
            dispatch(refreshPaymentList(criteria, page));

        });
    };
}