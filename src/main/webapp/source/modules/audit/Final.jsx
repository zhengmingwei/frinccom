import React from "react";
import {connect} from 'react-redux';
import {finalAccept, finalReject} from "actions/AuditActions";
import SubPage from 'modules/common/SubPage';
import CommodityDetail from 'modules/commodity/CommodityDetail';
import {getCommodity} from "actions/CommodityActions";
import {findByCommodityId} from "actions/AuditActions";
import moment from 'moment';
import {Form, Input, Radio, Button, DatePicker} from "antd";
const FormItem = Form.Item;
const createForm = Form.create;
const RadioGroup = Radio.Group;

class AuditAdd extends React.Component {

    constructor(props) {
        super(props);
    }
    state = {
        result: 1,
        commodityId: null
    }
    onChange = (e) => {
        this.setState({
            result: e.target.value,
        });
    }

    componentDidMount() {
        console.log(this)
        // this.setState({
        //     commodityId: this.props.params.id,
        // })
        this.props.dispatch(getCommodity(this.props.params.id))
        this.props.dispatch(findByCommodityId(this.props.params.id))
    }
    reset() {
        this.props.form.resetFields();
    }

    saveAudit(id) {
        this.props.form.validateFields((err, values) => {
            if (!err) {
                E.addOneTimeEventListener("toCommodityList14", (e) => {
                    this.props.history.push('/manager/commodity/list/14');
                })
                E.addOneTimeEventListener("toCommodityList11", (e) => {
                    this.props.history.push('/manager/commodity/list/11');
                })
                const rows = this.props.form.getFieldsValue();
                console.log(rows)
                if (this.state.result == 1) {
                    this.props.dispatch(finalAccept(rows));
                } else {
                    this.props.dispatch(finalReject(rows));
                }
            }

        })
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        const {selectedCommodity} = this.props;
        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 5},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 14,
                    offset: 6,
                },
            },
        };

        return (
            <SubPage breadcrumb="/后台管理/审核管理/审核认证">
			<Form>
                {getFieldDecorator('commodityId', {
                    initialValue: selectedCommodity.id,
                })(
                    <Input type="hidden"/>
                )}


<CommodityDetail />
				<FormItem
                    {...formItemLayout}
					label="选择审核结果"
				>

                        <RadioGroup onChange={this.onChange} value={this.state.result}>
                        <Radio value={1}>通过</Radio>
                        <Radio value={0}>不通过</Radio>
                        </RadioGroup>
				</FormItem>
                <FormItem
                    {...formItemLayout}
                    label="标识授权时间"
                    hasFeedback
                >
                    {getFieldDecorator('auditDate', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            message: "标识授权时间",
                        }],
                    })(
                        <DatePicker />
                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="标识有效期自"
                    hasFeedback
                >
                    {getFieldDecorator('beginDate', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            message: "标识有效期",
                        }],
                    })(
                        <DatePicker />
                    )}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="标识有效期至"
                    hasFeedback
                >
                    {getFieldDecorator('expireDate', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            message: "标识有效期至.",
                        }],
                    })(
                        <DatePicker />
                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="不通过原因"
                >
                    {getFieldDecorator('reason', { })(
                        <Input rows={4} />
                    )}

                </FormItem>
                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="button" size="large"
                            onClick={() => this.saveAudit(selectedCommodity.id)}>确定</Button>
                </FormItem>
			</Form>
            </SubPage>
        )
    }
}
;
AuditAdd = createForm()(AuditAdd);
export default  connect(
    state => {
        let {selectedCommodity} = state;
        return {
            selectedCommodity
        }
    }
)(AuditAdd);