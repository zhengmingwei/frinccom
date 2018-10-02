import React from "react";

import {Form, Input, Upload, Button, Icon, Select} from "antd";
import {showModalDialog} from "actions/CommonAction";
import {connect} from 'react-redux';
import {industryAndCategory, saveDictionary, getDictionary} from "actions/DictionaryActions";
import SubPage from 'modules/common/SubPage';
import {objToStrMap} from 'modules/common/CommonUtils';
import LimitUpload from 'modules/common/LimitUpload';
const FormItem = Form.Item;
const createForm = Form.create;
const Option = Select.Option;

class DictionaryForm extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(getDictionary(this.props.params.id));
        this.props.dispatch(industryAndCategory());
    }

    shouldComponentUpdate(nextProps, nextState) {
        const statusId = nextProps.params.id;
        const oldStatusId = this.props.params.id;

        if (statusId != oldStatusId){
            this.props.dispatch(getDictionary(statusId));
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
            this.props.history.push('/manager/dictionary/list');
        })
        this.props.form.validateFields((err, values) => {
            if (!err) {
                let rows = {};
                Object.assign(rows, this.props.form.getFieldsValue());
                this.props.dispatch(saveDictionary(rows))
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
        const {selectedDictionary:{id, value="", type="", weight="", parentId=""}} = this.props;
        console.log("type", type, value, weight,parentId)
        const {industryAndCategory} = this.props;
        const industryAndCategoryMap = objToStrMap(industryAndCategory);
        let industryOptions = [];
        industryOptions.push(<Option key="null" value="">--</Option>)
        if(industryAndCategoryMap)industryAndCategoryMap.forEach((optionValue, optionKey) => {
            industryOptions.push(<Option key={optionKey} value={optionKey}>{optionValue.value}</Option>);
        })

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
            <SubPage breadcrumb="/首页/后台管理/类别管理">
			<Form>
                {getFieldDecorator('id', {
                    initialValue: id,
                })(
                    <Input type="hidden"/>
                )}
				<FormItem
                    {...formItemLayout}
					label="类别"
				>
                    {getFieldDecorator('type', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            whitespace: false,
                            message: "请输入类别.",
                        }],
                        initialValue: type,
                        })(

                        <Select placeholder="类型">
                            <Option value="INDUSTRY">行业</Option>
                            <Option value="CATEGORY">行业类别</Option>
                            <Option value="REPORT_TYPE">检测报告类别</Option>
                        </Select>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="名称"
				>
                    {getFieldDecorator('value', {
                        initialValue: value,
                    })(
						<Input placeholder="请输入名称"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="显示顺序"
				>
                    {getFieldDecorator('weight', {
                        initialValue: weight,
                    })(
						<Input placeholder="显示顺序"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="所属行业"
				>
                    {getFieldDecorator('parentId', {
                        initialValue: parentId? parentId+"":"",
                    })(
						<Select >
                            {industryOptions}
                        </Select>
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
DictionaryForm = createForm()(DictionaryForm);
export default connect(
    state => {
        let {dictionaryList, selectedDictionary, industryAndCategory} = state;
        return {
            dictionaryList, selectedDictionary, industryAndCategory
        }
    }
)(DictionaryForm);