import {receiveSpecialItemList} from './SpecialItemActions';
import {receiveOtherQualificationList} from './OtherQualificationActions';

export const RECEIVE_COMMODITY_LIST = 'RECEIVE_COMMODITY_LIST';
export const SELECT_COMMODITY = 'SELECT_COMMODITY';
export const ADD_COMMODITY = 'ADD_COMMODITY';


export const NEW_A = 'NEW_A';



// export const CHANGE_COMMODITY_PIC = 'CHANGE_COMMODITY_PIC';
export const COMMODITY_CRITERIA_CHANGED = 'COMMODITY_CRITERIA_CHANGED';
export const ALL_COMMODITY_OPTIONS = 'ALL_COMMODITY_OPTIONS';

export function receiveCommodityList(data) {
    return {
        type: RECEIVE_COMMODITY_LIST,
        data: data
    }
}

export function selectCommodity(data) {
    return {
        type: SELECT_COMMODITY,
        data: data
    }
}

export function switchScene(data) {
    return {
        type: SWITCH_SCENE,
        data: data
    }
}

export function refreshCommodityList(criteria={}, page={}) {
    return dispatch => {
        E.doFind("manager/commodity/query", Object.assign({}, criteria, page));
        E.addOneTimeEventListener("commodityList", function (e) {
            dispatch(receiveCommodityList(e.data));
        });
    };
}

export function commodityCriteriaChanged(data) {
    return {
        type: COMMODITY_CRITERIA_CHANGED,
        data: data
    }
};

export function saveCommodity(commodity) {
    return dispatch => {
        E.doAdd("manager/commodity", commodity);

        E.addOneTimeEventListener("commodity", function (e) {
            if(e.data.returnCode == "200"){
                const commodity = {name: "", category: "", industry:"", pic:"",video:"", company:{}, factory:{}, brand:"",otherQualifications:[],specialItems:[]}
                dispatch(selectCommodity(commodity));
                E.dispatchEvent("tocommoditylist");
            } else {
                alert("保存失败");// alert(e.data.message);
            }
             // dispatch(selectCommodity(e.data));
            // dispatch(refreshcommodityList());
        });
    };
}

export function allCommodityOptions() {
    return dispatch => {
        E.doFind("manager/commodity/options");
        E.addOneTimeEventListener("commodityOptions", function (e) {
            dispatch(receiveCommodityOptions(e.data));
        });
    }
}

export function receiveCommodityOptions(data) {
    return {
        type: ALL_COMMODITY_OPTIONS,
        data: data
    }
}

export function getCommodity(id) {
    console.log(id)
    if(id){
        console.log("getcommodity",id)
        return dispatch => {
            E.doFind("/manager/commodity/"+id);
            E.addOneTimeEventListener("commodity", function (e) {
                if(e.data.returnCode == "200") {
                    const commodity = e.data.result;
                    dispatch(selectCommodity(commodity));
                    const {specialItems} = commodity;
                    const {otherQualifications} = commodity;
                    dispatch(receiveSpecialItemList(specialItems));
                    dispatch(receiveOtherQualificationList(otherQualifications));
                    E.dispatchEvent("toCommodity");
                } else {
                    console.log("查询失败")
                }
            });
        }
    } else {
        return dispatch => {
            const commodity = {name: "", category: "", industry:"", pic:"",video:"", company:{}, factory:{}, brand:"",otherQualifications:[],specialItems:[]}
            dispatch(selectCommodity(commodity));
            const {specialItems} = commodity;
            dispatch(receiveSpecialItemList(specialItems));
            const {otherQualifications} = commodity;
            dispatch(receiveOtherQualificationList(otherQualifications));
        }
    }
}

export function delCommodity(commodityId, criteria={}, page={}) {
    return dispatch => {
        E.doRemove("manager/commodity/"+ commodityId);
        console.warn(commodityId)
        E.addOneTimeEventListener("commodityDeleted", function (e) {
            dispatch(refreshCommodityList(criteria, page));

        });
    };
}
