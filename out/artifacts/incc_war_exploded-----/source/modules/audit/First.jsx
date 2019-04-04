import React from "react";
import {connect} from 'react-redux';
import {firstAccept, firstReject} from "actions/AuditActions";
import SubPage from 'modules/common/SubPage';
import CommodityDetail from 'modules/commodity/CommodityDetail';
import {getCommodity} from "actions/CommodityActions";
import moment from 'moment';
import {Form, Input, Radio, Button} from "antd";
import {findByCommodityId} from "actions/AuditActions";

const FormItem = Form.Item;
const createForm = Form.create;
const RadioGroup = Radio.Group;
const { TextArea } = Input;

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
        this.props.dispatch(getCommodity(this.props.params.id));
        this.props.dispatch(findByCommodityId(this.props.params.id));
    }
    reset() {
        this.props.form.resetFields();
    }

    saveAudit(id) {
        E.addOneTimeEventListener("toCommodityList6",  (e) => {
            this.props.history.push('/manager/commodity/list/6');
        })
        E.addOneTimeEventListener("toCommodityList7",  (e) => {
            this.props.history.push('/manager/commodity/list/7');
        })
        const reason = this.props.form.getFieldValue('reason')
        if( this.state.result == 1){
            this.props.dispatch(firstAccept({commodityId:id}));
        } else {
            this.props.dispatch(firstReject({commodityId:id, reason: reason}));
        }


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

                    <Input type="hidden" value={this.state.commodityId}/>

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