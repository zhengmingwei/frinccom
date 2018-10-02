import React from 'react';
import ReactDOM from 'react-dom';

import SubPage from 'modules/common/SubPage';

import {refreshDistributorList,distributorCriteriaChanged} from 'actions/DistributorActions';

import { Form, Input, Row, Col, Button, DatePicker } from 'antd';
import {showModalDialog} from 'actions/CommonAction';
const FormItem = Form.Item;
const createForm = Form.create;
const { RangePicker } = DatePicker;

/**
 * Distributor: zyj
 * Date: 16/12/21.
 * Time: 上午10:26.
 */
class DistributorSearch extends React.Component {

    constructor(props) {
        super(props);
    }

    search(){
        let rows =this.props.form.getFieldsValue();
        this.props.dispatch( refreshDistributorList(rows));
        this.props.dispatch(distributorCriteriaChanged(rows));
    }

    reset(){
        this.props.form.resetFields();
        this.props.dispatch(distributorCriteriaChanged());
    }

    //新增用户begin
    saveDistributor(){
        let rows =this.props.form.getFieldsValue();
        // console.log("***********");
        // console.log(rows);
        // console.log("***********");
    }

    //新增用户end
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
                            label="企业名称："
                        >
                            {getFieldDecorator('name', {})(
                                <Input placeholder="企业名称        " />
                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="联系人："
                        >
                            {getFieldDecorator('contact', {})(
                                <Input placeholder="联系人" />

                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <div className="ant-row ant-form-item"><div className="ant-col-9 ant-form-item-label">&nbsp;</div></div>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="组织机构代码："
                        >
                            {getFieldDecorator('orgCode', {})(
                                <Input placeholder="组织机构代码" />

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
DistributorSearch = createForm()(DistributorSearch);
export default DistributorSearch;