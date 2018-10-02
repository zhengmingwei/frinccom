import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux';
import * as UserReducer from './UserReducer';
import * as CommonReducer from './CommonReducer';
import * as CommodityReducer from './CommodityReducer';
import * as DistributorReducer from './DistributorReducer';
import * as SpecialItemReducer from './SpecialItemReducer';
import * as OtherQualificationReducer from './OtherQualificationReducer';
import * as PaymentReducer from './PaymentReducer';
import * as AuditReducer from './AuditReducer';
import * as DictionaryReducer from './DictionaryReducer';
import * as Precautionary from './PrecautionaryReducer';

const reducers = combineReducers({
	...UserReducer,
    ...CommonReducer,
    ...CommodityReducer,
    ...DistributorReducer,
    ...DictionaryReducer,
    ...SpecialItemReducer,
    ...OtherQualificationReducer,
    ...PaymentReducer,
    ...AuditReducer,
    ...Precautionary,
	routing: routerReducer
});

export default reducers;
