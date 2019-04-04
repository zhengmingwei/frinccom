export const RECEIVE_SPECIALITEM_LIST = 'RECEIVE_SPECIALITEM_LIST';
export const SELECT_SPECIALITEM = 'SELECT_SPECIALITEM';
export const ADD_SPECIALITEM = 'ADD_SPECIALITEM';
export const DELETE_SPECIALITEM = 'DELETE_SPECIALITEM';
export const CHANGE_SPECIALITEM_PIC = 'CHANGE_SPECIALITEM_PIC';


export function receiveSpecialItemList(data) {
    return {
        type: RECEIVE_SPECIALITEM_LIST,
        data: data
    }
}

export function selectSpecialItem(data) {
    return {
        type: SELECT_SPECIALITEM,
        data: data
    }
}

export function refreshSpecialItemList() {
    return dispatch => {
        E.doFind("manager/specialitem");
        E.addOneTimeEventListener("specialItemList", function (e) {
            dispatch(receivespecialItemList(e.data));
        });
    };
}

export function changeSpecialItemPic(data) {
    return {
        type: CHANGE_SPECIALITEM_PIC,
        data: data
    }
}

export function addSpecialItem(specialItem) {
    return dispatch => {
        E.doAdd("manager/specialitem", specialItem);

        E.addOneTimeEventListener("specialItem", function (e) {
             // dispatch(selectSpecialItem(e.data));
            // dispatch(refreshspecialItemList());
            dispatch(addSpecialItem2List(e.data.result));
        });
    };
}
export function addSpecialItem2List(data) {
    return {
        type: ADD_SPECIALITEM,
        data: data
    }
}
export function delSpecialItem(specialItemId) {
    return dispatch => {
        E.doRemove("manager/specialitem/"+ specialItemId);

        E.addOneTimeEventListener("specialItemDeleted", function (e) {
             // dispatch(selectSpecialItem(e.data));
            // dispatch(refreshspecialItemList());
            dispatch(delFromList(e.data))
        });
    };
}

export function delFromList(data){
    return {
        type: DELETE_SPECIALITEM,
        data: data.result
    }
}
