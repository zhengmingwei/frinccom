import React from 'react';
import SubPage from 'modules/common/SubPage';
import {refreshPaymentList} from 'actions/PaymentActions';
import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Button} from 'antd';
import PaymentList from './PaymentList';
import PaymentSearch from './PaymentSearch';

/**
 * Payment: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
class PaymentManager extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(refreshPaymentList());
    }

    view(id){
        this.history.push('/manager/payment/detail/'+ id);
    }

    edit(id){
        this.history.push('/manager/payment/form/'+ id);
    }

    delete(id){
        this.props.dispatch(refreshPaymentList(id));
    }

    render() {

        return (
            <div>
                <SubPage breadcrumb="/缴费信息查询">
                    <Row gutter={16} className="bottom-space">
                        <Col>
                            <PaymentSearch {...this.props}/>
                        </Col>
                    </Row>
                    <Row gutter={16}>
                        <Col>
                            <PaymentList {...this.props}  onEdit={this.edit} onView={this.view} onDelete={this.delete}/>
                        </Col>
                    </Row>
                </SubPage>
            </div>
        )
    }
};

export default connect(
    state => {
        let {paymentList, paymentStatus, paymentCriteria} = state;
        return {
            paymentList, paymentStatus, paymentCriteria
        }
    }
)(PaymentManager);