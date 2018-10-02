
import {
    RECEIVE_PAYMENT_LIST,
    PAYMENT_STATUS_CHANGED,
    PAYMENT_CRITERIA_CHANGED,
    SELECT_PAYMENT
} from 'actions/PaymentActions';

export function  paymentList(state = [], action){
    switch(action.type){
        case RECEIVE_PAYMENT_LIST:
            return action.data;
        default:
            return state;
    }
}

export function  paymentCriteria(state = [], action){
    switch(action.type){
        case PAYMENT_CRITERIA_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function  paymentStatus(state = [], action){
    switch(action.type){
        case PAYMENT_STATUS_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function selectedPayment(state = {id:null , transferDate: "", payer:"", fee:0, memo:"", commodityId:"", distributorId:""}, action) {
    switch (action.type){
        case SELECT_PAYMENT:
            return action.data;
        default:
            return state;
    }

}