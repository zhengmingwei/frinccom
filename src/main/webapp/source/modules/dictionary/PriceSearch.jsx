import React from 'react';
import ReactDOM from 'react-dom';

import SubPage from 'modules/common/SubPage';

import {refreshDictionaryList2,dictionaryCriteriaChanged1} from 'actions/DictionaryActions';

import { Form, Input, Row, Col, Button, Select } from 'antd';
import {showModalDialog} from 'actions/CommonAction';
const FormItem = Form.Item;
const createForm = Form.create;
const Option = Select.Option;

/**
 * Dictionary: zyj
 * Date: 16/12/21.
 * Time: 上午10:26.
 */
class PriceSearch extends React.Component {

    constructor(props) {
        super(props);
    }

    search(){
        let rows =this.props.form.getFieldsValue();
        console.log(rows)
        this.props.dispatch( refreshDictionaryList2(rows));
        this.props.dispatch(dictionaryCriteriaChanged1(rows));
    }

    reset(){
        this.props.form.resetFields();
        this.props.dispatch(dictionaryCriteriaChanged1());
    }

    //新增用户begin
    add(){
        this.props.onAdd();
        let rows =this.props.form.getFieldsValue();
        console.log("***********");
         console.log("88888888888888",rows);
        console.log("***********");
    }

    //新增用户end
    render() {
        const { getFieldDecorator } = this.props.form;
        const {dictionaryCriteria} = this.props;
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
                            label="名称："
                        >
                            {getFieldDecorator('value', {initialValue: dictionaryCriteria.value,})(
                                <Input placeholder="名称        " />
                            )}
                        </FormItem>
                    </Col>



                    <Col span={8}>
                        <Button type="primary" htmlType="submit" onClick={()=>this.search()}>搜索</Button>&nbsp;
                        <Button type="primary" htmlType="submit" onClick={()=>this.add()}>新建</Button>
                    </Col>
                </Row>

            </Form>
        )
    }
}
;
PriceSearch = createForm()(PriceSearch);
export default PriceSearch;