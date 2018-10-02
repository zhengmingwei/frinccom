import React from 'react';
import ReactDOM from 'react-dom';

import SubPage from 'modules/common/SubPage';

import {refreshPrecautionaryList,precautionaryCriteriaChange} from 'actions/PrecautionaryActions';

import { Form, Input, Row, Col, Button, DatePicker } from 'antd';
import {showModalDialog} from 'actions/CommonAction';
const FormItem = Form.Item;
const createForm = Form.create;
const { RangePicker } = DatePicker;

/**
 * Precautionary: zyj
 * Date: 16/12/21.
 * Time: 上午10:26.
 */
class PrecautionarySearch extends React.Component {

    constructor(props) {
        super(props);
    }

    search(){
        let data = {};
        data.companyName = this.props.form.getFieldValue("companyName");
        data.orgCode = this.props.form.getFieldValue("orgCode");
        data.commodityName = this.props.form.getFieldValue("commodityName");
        const auditDate = this.props.form.getFieldValue("auditDate");
        if (auditDate){
            data.beginAuditDate = auditDate[0];
            data.endAuditDate = auditDate[1];
        }
        this.props.dispatch( refreshPrecautionaryList(data));
        this.props.dispatch(precautionaryCriteriaChange(data));
    }

    reset(){
        this.props.form.resetFields();
        this.props.dispatch(precautionaryCriteriaChange());
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        const {precautionaryCriteria:{status}} = this.props;
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
                            {getFieldDecorator('companyName', {})(
                                <Input placeholder="企业名称        " />
                            )}
                        </FormItem>
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
                            label="授权时间"
                        >
                            {getFieldDecorator('auditDate', {})(
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
PrecautionarySearch = createForm()(PrecautionarySearch);
export default PrecautionarySearch;