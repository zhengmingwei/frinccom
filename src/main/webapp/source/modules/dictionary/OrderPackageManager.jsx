import React from 'react';
import SubPage from 'modules/common/SubPage';
import {refreshDictionaryList2,refreshPackageList2} from 'actions/DictionaryActions';

import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Button} from 'antd';
import OrderPackageList from './OrderPackageList';
//import DictionarySearch from './DictionarySearch';
import PriceSearch from './PriceSearch';


/*
 * Dictionary:  mingwei
 * Date: 20190419.
 * Time: 上午  10:30.  50
 */
class OrderPackageManager extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(refreshPackageList2());
    }

    view(id){
        this.history.push('/manager/dictionary/detail/'+ id);
    }

    add(){
        this.history.push('/manager/orderPriceSystem/Priceform');

    }

    edit(id){

        console.log(" Manager中的 修改 获取ID********************");
        console.log(id);
        //this.history.push('/manager/orderPriceSystem/Priceform/'+ id);
        this.history.push('/manager/orderPriceSystem/Priceform/'+ id);
    }

    delete(id){
        this.props.dispatch(refreshDictionaryList2(id));
    }

    render() {

        return (
            <div>
                <SubPage breadcrumb="/认证管理中心/我的套餐">
                    {/*<Row gutter={16} className="bottom-space">
                        <Col>
                            <PriceSearch {...this.props} onAdd={this.add}/>
                        </Col>
                    </Row>*/}
                    <Row gutter={16}>
                        <Col>
                            <OrderPackageList {...this.props}/>
                        </Col>
                    </Row>
                </SubPage>
            </div>
        )
    }
};

export default connect(
    state => {
        let {dictionaryList2,orderPackageList2, dictionaryStatus, dictionaryCriteria} = state;
        return {
            dictionaryList2, orderPackageList2, dictionaryStatus, dictionaryCriteria
        }
    }
)(OrderPackageManager);