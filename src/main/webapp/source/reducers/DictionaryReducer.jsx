        
import {
    RECEIVE_DICTIONARY_LIST,
    DICTIONARY_STATUS_CHANGED,
    DICTIONARY_CRITERIA_CHANGED,
    DICTIONARY_CRITERIA_CHANGED1,
    ORDERPACKAGE_CRITERIA_CHANGED,
    ORDERPACKAGE_CRITERIA_CHANGED1,
    INDUSTRYS_CATEGORYS,
    SELECT_PRICESYSTEM,
    RECEIVE_DICTIONARY_LIST2,
    RECEIVE_ORDERPACKAGE_LIST2,
    RECEIVE_ORDERPACKAGE_LIST,
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

//=================我的套餐====================
export function  orderPackageList22(state = [], action){

    console.log(" reducer  ===================我的套餐=====================",action)
    console.log(" reducer  ===================我的套餐===action.type==================",action.type)
    console.log(" reducer  ===================我的套餐===action.data==================",action.data)
    switch(action.type){
        case RECEIVE_ORDERPACKAGE_LIST:
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

//==================订购套餐==================
export function  orderPackageList2(state = [], action){
    switch(action.type){
        case RECEIVE_ORDERPACKAGE_LIST2:
            return action.data;
        default:
            return state;
    }
}
//==================订购套餐==================
export function  orderPackageCriteria1(state = [], action){
    switch(action.type){
        case ORDERPACKAGE_CRITERIA_CHANGED1:
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

//export function selectPriceSystem(state = { id:"",name:"",describe:"",price:"",total:"",createTime:"",endTime:""}, action) {
export function selectPriceSystem(state = { id:"",name:"",describe:"",price:"",total:"",createTimes:"",endTimes:""}, action) {

    switch (action.type){
        case SELECT_PRICESYSTEM:
            return action.data;
        default:
            return state;
    }
}
