<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('List Tickets'), ['action' => 'index']) ?></li>
        <li><?= $this->Html->link(__('List Destinations'), ['controller' => 'Destinations', 'action' => 'index']) ?></li>
        <li><?= $this->Html->link(__('New Destination'), ['controller' => 'Destinations', 'action' => 'add']) ?></li>
    </ul>
</nav>
<div class="tickets form large-9 medium-8 columns content">
    <?= $this->Form->create($ticket) ?>
    <fieldset>
        <legend><?= __('Add Ticket') ?></legend>
        <?php
            echo $this->Form->input('destination_id', ['options' => $destinations]);
            echo $this->Form->input('serial_number',array('default'=>$serialnumber));
            echo $this->Form->input('sold',['type' => 'checkbox']);
            echo $this->Form->input('validity', ['empty' => true]);
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
