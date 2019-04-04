import React from 'react';
import ReactDOM from 'react-dom';

import SubPage from 'modules/common/SubPage';

import {refreshCommodityList,commodityCriteriaChanged} from 'actions/CommodityActions';

import { Form, Input, Row, Col, Button, DatePicker } from 'antd';
import {showModalDialog} from 'actions/CommonAction';
const FormItem = Form.Item;
const createForm = Form.create;
const { RangePicker } = DatePicker;

/**
 * Commodity: zyj
 * Date: 16/12/21.
 * Time: 上午10:26.
 */
class CommoditySearch extends React.Component {

    constructor(props) {
        super(props);
    }

    search(){
        let rows =this.props.form.getFieldsValue();
        this.props.dispatch( refreshCommodityList(rows));
        this.props.dispatch(commodityCriteriaChanged(rows));
    }

    reset(){
        this.props.form.resetFields();
        this.props.dispatch(commodityCriteriaChanged());
    }

    //新增用户begin
    saveCommodity(){
        let rows =this.props.form.getFieldsValue();
        // console.log("***********");
        // console.log(rows);
        // console.log("***********");
    }

    //新增用户end
    render() {
        const { getFieldDecorator } = this.props.form;
        const {commodityCriteria:{status}} = this.props;
        const formItemLayout = {
            labelCol: { span: 9 },
            wrapperCol: { span: 15 },
            width:"100%",
        };


        return (
            <Form>
                {getFieldDecorator('status', {
                    initialValue: status,
                })(
                    <Input type="hidden"/>
                )}
                <Row gutter={40}>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="企业名称："
                        >
                            {getFieldDecorator('cname', {})(
                                <Input placeholder="企业名称        " />
                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="组织机构代码："
                        >
                            {getFieldDecorator('code', {})(
                                <Input placeholder="组织机构代码" />

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
                            {getFieldDecorator('name', {})(
                                <Input placeholder="产品名称" />
                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="发布时间"
                        >
                            {getFieldDecorator('pubDate', {})(
                                <RangePicker />
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
CommoditySearch = createForm()(CommoditySearch);
export default CommoditySearch;