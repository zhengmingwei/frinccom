import React from 'react';
import SubPage from 'modules/common/SubPage';
import {refreshDictionaryList2} from 'actions/DictionaryActions';

import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Button} from 'antd';
import DictionaryList2 from './DictionaryList2';
//import DictionarySearch from './DictionarySearch';
import PriceSearch from './PriceSearch';

/**
 * Dictionary:  YANGHUI
 * Date: 20190327.
 * Time: 上午  09:42.  50
 */
class DictionaryManager2 extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(refreshDictionaryList2());
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
                <SubPage breadcrumb="/系统管理/价格体系管理">
                    <Row gutter={16} className="bottom-space">
                        <Col>
                            <PriceSearch {...this.props} onAdd={this.add}/>
                        </Col>
                    </Row>
                 <Row gutter={16}>
                        <Col>
                            <DictionaryList2 {...this.props}  onEdit={this.edit} onView={this.view} onDelete={this.delete}/>
                        </Col>
                    </Row>
                </SubPage>
            </div>
        )
    }
};

export default connect(
    state => {
        let {dictionaryList2, dictionaryStatus, dictionaryCriteria} = state;
        return {
            dictionaryList2, dictionaryStatus, dictionaryCriteria
        }
    }
)(DictionaryManager2);