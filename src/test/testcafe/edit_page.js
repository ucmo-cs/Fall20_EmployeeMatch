import { Selector } from 'testcafe';

fixture `Car Fixture`
    .page `http://localhost:8080`;

test('Edit Test', async t => {
    await t
        .click(Selector('td').withText('Edit').find('.btn.btn-success').nth(1))
        .selectText(Selector('[name="make"].form-control'))
        .typeText(Selector('[name="make"].form-control'), 'DMC')
        .selectText(Selector('[name="model"].form-control'))
        .typeText(Selector('[name="model"].form-control'), 'DeLorean')
        .selectText(Selector('[name="year"].form-control'))
        .typeText(Selector('[name="year"].form-control'), '1981')
        .click(Selector('button').withText('Save'))
        .expect(Selector('tbody > tr > td').nextSibling(0).innerText).eql('DMC')
        .expect(Selector('tbody > tr > td').nextSibling(1).innerText).eql('DeLorean')
        .expect(Selector('tbody > tr > td').nextSibling(2).innerText).eql('1981');
});

test('Add Test', async t => {
    await t
        .click(Selector('button').withText('Add'))
        .selectText(Selector('[name="make"].form-control'))
        .typeText(Selector('[name="make"].form-control'), 'Pontiac')
        .selectText(Selector('[name="model"].form-control'))
        .typeText(Selector('[name="model"].form-control'), 'TransAm')
        .selectText(Selector('[name="year"].form-control'))
        .typeText(Selector('[name="year"].form-control'), '1993')
        .click(Selector('button').withText('Save'))
        .expect(Selector('tr').nth(-1).child().nextSibling(0).innerText).eql('Pontiac')
        .expect(Selector('tr').nth(-1).child().nextSibling(1).innerText).eql('TransAm')
        .expect(Selector('tr').nth(-1).child().nextSibling(2).innerText).eql('1993');
});

test('Delete Test', async t => {
    await t
        .click(Selector('td').withText('Delete').nth(-1).find('.btn.btn-success').nth(0))
        .expect(Selector('tr').nth(-1).child().nextSibling(0).innerText).notEql('Pontiac')
        .expect(Selector('tr').nth(-1).child().nextSibling(1).innerText).notEql('TransAm')
        .expect(Selector('tr').nth(-1).child().nextSibling(2).innerText).notEql('1993');
});
