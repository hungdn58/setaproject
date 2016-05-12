<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Form->postLink(
                __('Delete'),
                ['action' => 'delete', $footprint->id],
                ['confirm' => __('Are you sure you want to delete # {0}?', $footprint->id)]
            )
        ?></li>
        <li><?= $this->Html->link(__('List Footprints'), ['action' => 'index']) ?></li>
    </ul>
</nav>
<div class="footprints form large-9 medium-8 columns content">
    <?= $this->Form->create($footprint) ?>
    <fieldset>
        <legend><?= __('Edit Footprint') ?></legend>
        <?php
            echo $this->Form->input('visitor');
            echo $this->Form->input('createDate', ['empty' => true]);
            echo $this->Form->input('delFlg');
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
