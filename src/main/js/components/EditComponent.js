import React, { Component } from 'react'
import ApiService from "../services/ApiService";

class EditComponent extends Component {

    constructor(props){
        super(props);
        this.state ={
            id: '',
            make: '',
            model: '',
            year: '',
        }
        this.saveCar = this.saveCar.bind(this);
        this.loadCar = this.loadCar.bind(this);
    }

    componentDidMount() {
        this.loadCar();
    }

    loadCar() {
        ApiService.fetchCarById(window.localStorage.getItem("carId"))
            .then((res) => {
                let car = res.data;
                this.setState({
                    id: car.id,
                    make: car.make,
                    model: car.model,
                    year: car.year,
                })
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    saveCar = (e) => {
        e.preventDefault();
        let car = {id: this.state.id, make: this.state.make, model: this.state.model, year: this.state.year};
        ApiService.editCar(car)
            .then(res => {
                this.setState({message : 'Car added successfully.'});
                this.props.history.push('/');
            });
    }

    validate() {
        return this.state.year >= 1900 && this.state.year <=2020;
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Edit Car</h2>
                <form>

                    <div className="form-group">
                        <label>Make:</label>
                        <input placeholder="Make" name="make" className="form-control" value={this.state.make} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>Model:</label>
                        <input placeholder="Model" name="model" className="form-control" value={this.state.model} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>Year:</label>
                        <input placeholder="Year" name="year" className="form-control" value={this.state.year} onChange={this.onChange}/>
                        <font color="red">{!this.validate() ? 'Year Error: Year must be >= 1900 and <=2020' : ""}</font>
                    </div>

                    <button className="btn btn-success" disabled={!this.validate()} onClick={this.saveCar}>Save</button>
                </form>
            </div>
        );
    }
}

export default EditComponent;