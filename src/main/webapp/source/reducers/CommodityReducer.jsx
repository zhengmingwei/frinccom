
import {
    RECEIVE_COMMODITY_LIST,
    COMMODITY_STATUS_CHANGED,
    COMMODITY_CRITERIA_CHANGED,
    ALL_COMMODITY_OPTIONS,
    SELECT_COMMODITY,
    NEW_A
} from 'actions/CommodityActions';

export function  commodityList(state = [], action){
    switch(action.type){
        case RECEIVE_COMMODITY_LIST:
            return action.data;
        default:
            return state;
    }
}

export function  commodityCriteria(state = [], action){
    switch(action.type){
        case COMMODITY_CRITERIA_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function  commodityStatus(state = [], action){
    switch(action.type){
        case COMMODITY_STATUS_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function  commodityOptions(state = [], action){
    switch(action.type){
        case ALL_COMMODITY_OPTIONS:
            return action.data;
        default:
            return state;
    }
}

export function selectedCommodity(state = {name:"", category:"", industry:"1", pic:"",video:"",company:[], factory:[], brand:{}}, action) {
    switch (action.type){
        case SELECT_COMMODITY:
            return action.data;
        default:
            return state;
    }

}


