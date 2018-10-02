export const RECEIVE_PRECAUTIONARY_LIST = 'RECEIVE_PRECAUTIONARY_LIST';
export const PRECAUTIONARY_CRITERIA = 'PRECAUTIONARY_CRITERIA';

export function receivePrecautionaryList(data) {
    return {
        type: RECEIVE_PRECAUTIONARY_LIST,
        data: data
    }
}

export function refreshPrecautionaryList(criteria={}, page={}) {
    return dispatch => {
        E.doFind("manager/precautionary/query", Object.assign({}, criteria, page));
        E.addOneTimeEventListener("precautionaryList", function (e) {
            dispatch(receivePrecautionaryList(e.data));
        });
    };
}

export function precautionaryCriteriaChange(data) {
    return {
        type: PRECAUTIONARY_CRITERIA,
        data: data
    }
}
