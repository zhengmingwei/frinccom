import React from 'react';
import ReactDOM from 'react-dom';

import SubPage from 'modules/common/SubPage';

import {refreshPaymentList,paymentCriteriaChanged} from 'actions/PaymentActions';

import { Form, Input, Row, Col, Button, DatePicker } from 'antd';
import {showModalDialog} from 'actions/CommonAction';
const FormItem = Form.Item;
const createForm = Form.create;
const { RangePicker } = DatePicker;

/**
 * Payment: zyj
 * Date: 16/12/21.
 * Time: 上午10:26.
 */
class PaymentSearch extends React.Component {

    constructor(props) {
        super(props);
    }

    search(){
        let rows =this.props.form.getFieldsValue();
        this.props.dispatch( refreshPaymentList(rows));
        this.props.dispatch(paymentCriteriaChanged(rows));
    }

    reset(){
        this.props.form.resetFields();
        this.props.dispatch(paymentCriteriaChanged());
    }


    savePayment(){
        let rows =this.props.form.getFieldsValue();
        // console.log("***********");
        // console.log(rows);
        // console.log("***********");
    }


    render() {
        const { getFieldDecorator } = this.props.form;
        const formItemLayout = {
            labelCol: { span: 9 },
            wrapperCol: { span: 15 },
            width:"100%",
        };


        return (
            <Form>
                <Row gutter={40}>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="收费机构名称："
                        >
                            {getFieldDecorator('distributorName', {})(
                                <Input placeholder="收费机构名称        " />
                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="缴费人："
                        >
                            {getFieldDecorator('payer', {})(
                                <Input placeholder="缴费人" />

                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <div className="ant-row ant-form-item"><div className="ant-col-9 ant-form-item-label">&nbsp;</div></div>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="产品名称："
                        >
                            {getFieldDecorator('commodityName', {})(
                                <Input placeholder="产品名称" />

                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="电话："
                        >
                            {getFieldDecorator('phone', {})(
                                <Input placeholder="产品名称" />
                            )}
                        </FormItem>
                    </Col>

                    <Col span={8}>
                        <Button type="primary" htmlType="submit" onClick={()=>this.search()}>搜索</Button>
                    </Col>
                </Row>

            </Form>
        )
    }
}
;
PaymentSearch = createForm()(PaymentSearch);
export default PaymentSearch;