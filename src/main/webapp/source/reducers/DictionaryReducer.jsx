        
import {
    RECEIVE_DICTIONARY_LIST,
    DICTIONARY_STATUS_CHANGED,
    DICTIONARY_CRITERIA_CHANGED,
    DICTIONARY_CRITERIA_CHANGED1,
    INDUSTRYS_CATEGORYS,
    SELECT_PRICESYSTEM,
    RECEIVE_DICTIONARY_LIST2,
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
//=================价格体系====================
export function  dictionaryList2(state = [], action){
    switch(action.type){
        case RECEIVE_DICTIONARY_LIST2:
            return action.data;
        default:
            return state;
    }
}

//==================价格体系完==================
export function  dictionaryCriteria(state = [], action){
    switch(action.type){
        case DICTIONARY_CRITERIA_CHANGED:
            return action.data;
        default:
            return state;
    }
}

//==================价格体系完==================
export function  dictionaryCriteria1(state = [], action){
    switch(action.type){
        case DICTIONARY_CRITERIA_CHANGED1:
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

export function selectedDictionary(state = {id:"",name:"",value: "", orgCode: "", legalPerson:"", contact:"", phone:"", fax:"", mail:"",province:"",address:"",businessLicense:"",orgCodeCertification:"",taxCertificate:""}, action) {
    switch (action.type){
        case SELECT_DICTIONARY:
            return action.data;
        default:
            return state;
    }
}


//=========================价格体系修改=========================

export function selectPriceSystem(state = { id:"",name:"",describe:"",price:"",total:"",createTime:"",endTime:""}, action) {
    console.log("reducer  =================价格体系修改=========================selectPriceSystem");
    switch (action.type){
        case SELECT_PRICESYSTEM:
            return action.data;
        default:
            return state;
    }
}
