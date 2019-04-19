import React from 'react';
import {render} from 'react-dom';
import {compose} from 'redux';
import {Provider} from 'react-redux';

import App from 'containers/ManagerApp';
import Welcome from 'modules/main/Welcome';
import Test01 from 'modules/main/Test01';
import UserManager from 'modules/usermanager/UserManager';
import UserDetail from 'modules/usermanager/Detail';
import UserForm from 'modules/usermanager/Form';
import Password from 'modules/usermanager/Password';
import DistributorForm from 'modules/distributor/Form';
import DistributorDetail from 'modules/distributor/Detail';
import DistributorManager from 'modules/distributor/DistributorManager';
import CommodityForm from 'modules/commodity/CommodityForm';
import CommodityDetail from 'modules/commodity/CommodityDetail';
import AuditCommodityDetail from 'modules/commodity/Detail';
import CommodityManager from 'modules/commodity/CommodityManager';
import PaymentForm from 'modules/payment/Form';
import PaymentManager from 'modules/payment/PaymentManager';
import First from 'modules/audit/First';
import Third from 'modules/audit/Third';
import Final from 'modules/audit/Final';
import DictionaryManager from 'modules/dictionary/DictionaryManager';
import PricingStructureManager from 'modules/dictionary/PricingStructureManager';
import PriceSysBuyManager from 'modules/dictionary/PriceSysBuyManager';
import DictionaryForm from 'modules/dictionary/Form';

import PriceForm from 'modules/dictionary/PriceForm';
import precautionaryManager from 'modules/precautionary/precautionaryManager';


import configureStore from 'store/configureStore';
import DevTools from 'containers/DevTools';
import {Router, Route, hashHistory, IndexRoute, Link} from 'react-router';
import {syncHistoryWithStore} from 'react-router-redux';
import Network from 'tigerfacejs-network';
import {Menu} from "antd";



const enhancer = compose(
    DevTools.instrument()
);

const store = configureStore(enhancer);

const history = syncHistoryWithStore(hashHistory, store);

/*<Route path="/test/test01" component={Test01}/>
 <Route path="/test/test02" component={Test02}/>
 <Route path="/demo/person" component={PersonManager}/>
 <Route path="/test/shunze" component={ShunzeManager}/>
 <Route path="/demo/person1" component={ShunZePractise}/>*/
render(
    <Provider store={store}>
        <div>
            <Router history={history}>
                <Route path="/" component={App}>
                    <IndexRoute component={Welcome}/>
                    <Route path="/test/test01" component={Test01}/>

                    <Route path="/manager/user/detail/:id" component={UserDetail}/>
                    <Route path="/manager/user/form" component={UserForm}/>
                    <Route path="/manager/user/form/:id" component={UserForm}/>
                    <Route path="/manager/user/list" components={UserManager}/>
                    <Route path="/manager/user/password" components={Password}/>

                    <Route path="/manager/distributor/detail/:id" component={DistributorDetail}/>
                    <Route path="/manager/distributor/form" component={DistributorForm}/>
                    <Route path="/manager/distributor/form/:id" component={DistributorForm}/>
                    <Route path="/manager/distributor/list" component={DistributorManager}/>

                    <Route path="/manager/commodity/detail/:id" component={CommodityDetail}/>
                    <Route path="/manager/commodity/audit/detail/:id" component={AuditCommodityDetail}/>
                    <Route path="/manager/commodity/form" components={CommodityForm}/>
                    <Route path="/manager/commodity/form/:id" components={CommodityForm}/>
                    <Route path="/manager/commodity/list/:id" components={CommodityManager}/>

                    <Route path="/manager/payment/form" components={PaymentForm}/>
                    <Route path="/manager/payment/form/:id" components={PaymentForm}/>
                    <Route path="/manager/payment/list" components={PaymentManager}/>

                    <Route path="/manager/audit/first/:id" components={First}/>
                    <Route path="/manager/audit/third/:id" components={Third}/>
                    <Route path="/manager/audit/final/:id" components={Final}/>

                    <Route path="/manager/dictionary/list" components={DictionaryManager}/>
                    
                    <Route path="/manager/orderPriceSystem/query2" components={PricingStructureManager}/>
                    <Route path="/manager/orderPriceSystem/queryBuy" components={PriceSysBuyManager}/>

                    <Route path="/manager/dictionary/form" components={DictionaryForm}/>
                    <Route path="/manager/dictionary/form/:id" components={DictionaryForm}/>
                    <Route path="/manager/orderPriceSystem/Priceform" components={PriceForm}/>
                    <Route path="/manager/orderPriceSystem/Priceform/:id" components={PriceForm}/>



                    <Route path="/manager/precautionary/list/:status" components={precautionaryManager}/>
                </Route>
            </Router>
            {/*<DevTools />*/}
        </div>
    </Provider>,
    document.getElementById('entry')
);


