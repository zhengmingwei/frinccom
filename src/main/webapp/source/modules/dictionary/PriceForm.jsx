import React from "react";

import {Form, Input, Upload, Button, Icon, Select, InputNumber,DatePicker} from "antd";
import {showModalDialog} from "actions/CommonAction";
import {connect} from 'react-redux';
import {industryAndCategory, saveOrderPriceSystem, getDictionary2} from "actions/DictionaryActions";
import SubPage from 'modules/common/SubPage';
import {objToStrMap} from 'modules/common/CommonUtils';
import LimitUpload from 'modules/common/LimitUpload';

import moment from 'moment';



const FormItem = Form.Item;
const createForm = Form.create;
const Option = Select.Option;
const { TextArea } = Input;
class PriceForm extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(getDictionary2(this.props.params.id));
        this.props.dispatch(industryAndCategory());
    }

    shouldComponentUpdate(nextProps, nextState) {
        const statusId = nextProps.params.id;
        const oldStatusId = this.props.params.id;

        if (statusId != oldStatusId){
            this.props.dispatch(getDictionary2(statusId));
            return false;
        }
        return true;

    }

    reset() {
        this.props.form.resetFields();
        //this.props.dispatch(userCriteriaChanged());
    }


    handleSubmit() {
        E.addOneTimeEventListener("todictionarylist",  (e) => {
            this.props.history.push('/manager/orderPriceSystem/query2');
        })
        this.props.form.validateFields((err, values) => {
            if (!err) {
                let rows = {};
                Object.assign(rows, this.props.form.getFieldsValue());
                this.props.dispatch(saveOrderPriceSystem(rows))
            } else {
                console.error('validate failed');
            }
        })
    }
    normFile=(e)=> {
        console.log('Upload event:', e);
        return e ;
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        console.log(getFieldDecorator)
        const {selectPriceSystem:{id="",name="",describe="",price="",total="",createTime="",endTime=""}} = this.props;

        let cBusinessBegin = createTime?moment(createTime):moment();
        let cBusinessEnd = endTime?moment(endTime):moment();

        //console.log("type", type, value, weight,parentId)
       /* const {industryAndCategory} = this.props;
        const industryAndCategoryMap = objToStrMap(industryAndCategory);
        let industryOptions = [];
        industryOptions.push(<Option key="null" value="">--</Option>)
        if(industryAndCategoryMap)industryAndCategoryMap.forEach((optionValue, optionKey) => {
            industryOptions.push(<Option key={optionKey} value={optionKey}>{optionValue.value}</Option>);
        })*/

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
        const doubleFormItemLayout = {
            labelCol: {
                xs: {span: 12},
                sm: {span: 6},
            },
            wrapperCol: {
                xs: {span: 12},
                sm: {span: 12},
            }
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
            <SubPage breadcrumb="/首页/后台管理/价格体系管理">
			<Form>
                <FormItem
                    {...formItemLayout}
                    label="序号"
                >
                    {getFieldDecorator('id', {
                        initialValue: id,
                    })(
                        <Input readOnly placeholder="自动生成"/>
                    )}
                </FormItem>
				<FormItem
                    {...formItemLayout}
					label="名称"
				>
                    {getFieldDecorator('name', {
                        initialValue: name,
                    })(
						<Input placeholder="请输入名称"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="描述"
				>
                    {getFieldDecorator('describe', {
                        initialValue: describe,
                    })(
						<Input type="textarea" />
                    )}
				</FormItem>
                <FormItem
                    {...formItemLayout}
                    label="价格"
                >
                    {getFieldDecorator('price', {
                        initialValue: price,
                    })(
                        <InputNumber placeholder="价格" min={1} max={999999999} />
                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="数量"
                >
                    {getFieldDecorator('total', {
                        initialValue: total,
                    })(
                        <InputNumber placeholder="数量" min={0} max={999999999} />
                    )}
                </FormItem>
                <FormItem
                    {...doubleFormItemLayout}
                    label="期限自"
                    hasFeedback
                >
                    {getFieldDecorator('createTimes', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            message: "请输入期限自",
                        }],
                        initialValue: moment(cBusinessBegin),
                    })(
                        <DatePicker  showTime format="YYYY-MM-DD"/>
                    )}
                </FormItem>
                <FormItem
                    {...doubleFormItemLayout}
                    label="期限至"
                    hasFeedback
                >
                    {getFieldDecorator('endTimes', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            message: "请输入期限至",
                        }],
                        initialValue: moment(cBusinessEnd) ,
                    })(
                        <DatePicker  showTime format="YYYY-MM-DD"/>
                    )}
                </FormItem>
                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="button" size="large" onClick={()=>this.handleSubmit()}>确定</Button>

                    <Button type="primary" htmlType="reset" size="large" onClick={()=>this.reset()} style={{ marginLeft: 8 }}>重置</Button>
                </FormItem>

			</Form>

            </SubPage>
        )
    }
}
;
PriceForm = createForm()(PriceForm);
export default connect(
    state => {
        let {dictionaryList2, selectPriceSystem, industryAndCategory} = state;
        return {
            dictionaryList2, selectPriceSystem, industryAndCategory
        }
    }
)(PriceForm);