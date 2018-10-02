
import {
    RECEIVE_DICTIONARY_LIST,
    DICTIONARY_STATUS_CHANGED,
    DICTIONARY_CRITERIA_CHANGED,
    INDUSTRYS_CATEGORYS,
    SELECT_DICTIONARY
} from 'actions/DictionaryActions';

export function  dictionaryList(state = [], action){
    switch(action.type){
        case RECEIVE_DICTIONARY_LIST:
            return action.data;
        default:
            return state;
    }
}

export function  dictionaryCriteria(state = [], action){
    switch(action.type){
        case DICTIONARY_CRITERIA_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function  dictionaryStatus(state = [], action){
    switch(action.type){
        case DICTIONARY_STATUS_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function  industryAndCategory(state = [], action){
    switch(action.type){
        case INDUSTRYS_CATEGORYS:
            return action.data;
        default:
            return state;
    }
}

export function selectedDictionary(state = {name: "", orgCode: "", legalPerson:"", contact:"", phone:"", fax:"", mail:"",province:"",address:"",businessLicense:"",orgCodeCertification:"",taxCertificate:""}, action) {
    switch (action.type){
        case SELECT_DICTIONARY:
            return action.data;
        default:
            return state;
    }
}