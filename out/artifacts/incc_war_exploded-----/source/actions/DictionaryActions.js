export const RECEIVE_DICTIONARY_LIST = 'RECEIVE_DICTIONARY_LIST';
export const SELECT_DICTIONARY = 'SELECT_DICTIONARY';
export const ADD_DICTIONARY = 'ADD_DICTIONARY';
export const CHANGE_DICTIONARY_PIC = 'CHANGE_DICTIONARY_PIC';
export const INDUSTRYS_CATEGORYS = 'INDUSTRYS_CATEGORYS';
export const DICTIONARY_CRITERIA_CHANGED = 'DICTIONARY_CRITERIA_CHANGED';



export function receiveDictionaryList(data) {
    return {
        type: RECEIVE_DICTIONARY_LIST,
        data: data
    }
}

export function selectDictionary(data) {
    return {
        type: SELECT_DICTIONARY,
        data: data
    }
}

export function switchScene(data) {
    return {
        type: SWITCH_SCENE,
        data: data
    }
}

export function dictionaryCriteriaChanged(data) {
    return {
        type: DICTIONARY_CRITERIA_CHANGED,
        data: data
    }
}

export function refreshDictionaryList(criteria={}) {
    return dispatch => {
        E.doFind("manager/dictionary/query", Object.assign({}, criteria));
        E.addOneTimeEventListener("dictionaryList", function (e) {
            dispatch(receiveDictionaryList(e.data));
        });
    };
}

export function saveDictionary(dictionary) {
    return dispatch => {
        E.doAdd("manager/dictionary", dictionary);

        E.addOneTimeEventListener("dictionary", function (e) {

            if(e.data.returnCode == "200") {
                E.dispatchEvent("todictionarylist");
            } else {
                alert("保存失败")
            }
             // dispatch(selectDictionary(e.data));
            // dispatch(refreshdictionaryList());
        });
    };
}

export function industryAndCategory() {
    return dispatch => {
        E.doFind("manager/dictionary/industryAndCategory");
        E.addOneTimeEventListener("industryAndCategory", function (e) {
            dispatch(receiveIndustryAndCategory(e.data));
        });
    }
}

export function receiveIndustryAndCategory(data) {
    return {
        type: INDUSTRYS_CATEGORYS,
        data: data
    }
}

export function getDictionary(id) {
    if(id){
        return dispatch => {
            E.doFind("/manager/dictionary/"+id);
            E.addOneTimeEventListener("dictionary", function (e) {
                if(e.data.returnCode == "200") {
                    const dictionary = e.data.result;
                    dispatch(selectDictionary(dictionary));
                } else {
                    console.log("查询失败")
                }
            });
        }
    } else {
        return dispatch => {
            const dictionary = {value: "", type: "", weight:"", parentId:""}
            dispatch(selectDictionary(dictionary));
        }
    }
}

export function delDictionary(dictionaryId, criteria={}, page={}) {
    return dispatch => {
        E.doRemove("manager/dictionary/"+ dictionaryId);
        console.warn(dictionaryId)
        E.addOneTimeEventListener("dictionaryDeleted", function (e) {
            dispatch(refreshDictionaryList(criteria, page));

        });
    };
}